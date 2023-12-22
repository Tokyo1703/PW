package src.Negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.time.temporal.ChronoUnit;


import src.Datos.DAO.AsistenteDAO;
import src.Datos.DAO.CampamentoDAO;
import src.Datos.DAO.InscripcionDAO;
import src.Negocio.DTO.CampamentoDTO;
import src.Negocio.DTO.InscripcionDTO;
import src.Negocio.DTO.Enum.Registro;
import src.Negocio.DTO.Enum.TipoInscripcion;



public class gestorInscripciones
{
    /**
     * Variable que representa el codigo de error que se puede prodicir.
     */
    private int error;
    
    /**
     * Variable CampamentoDAO, representa una instancia de la clase CampamentoDAO usada para la conexion con la base de datos.
     * Variable ActividadDAO, representa una instancia de la clase ActividadDAO usada para la conexion con la base de datos.
     * Variable InscripcionDAO, representa una instancia de la clase InscripcionDAO usada para la conexion con la base de datos.
     */
    private InscripcionDAO Inscripcion_DAO; 
    private CampamentoDAO Campamento_DAO;
    private AsistenteDAO Asistente_DAO;

    public gestorInscripciones(Properties sql, Properties config){
        Inscripcion_DAO = new InscripcionDAO(sql, config);
        Campamento_DAO = new CampamentoDAO(sql,config);
        Asistente_DAO = new AsistenteDAO(sql, config);
    }

    /**
     * Metodo usado para añadir una Inscriocion si se cumplen los requisitos
     * @param idAsistente id del asistente a inscribir
     * @param idCampamento id del campamento en el que se inscribe
     * @param tipo tipo de inscripcion que realiza
     * @return true si se registra correctamente, false si se produce algun error
     */

    public boolean realizarInscripcion(int idAsistente, int idCampamento, TipoInscripcion tipo){
        InscripcionDTO inscripcion;

        LocalDate fecha=LocalDate.now();
        if (!Asistente_DAO.existeID(idAsistente))
        {
            error = 0;
            return false;
        }
        if (!Campamento_DAO.existeID(idCampamento))
        {
            error = 1;
            return false;
        }
        
        CampamentoDTO camp = Campamento_DAO.buscarCampamento(idCampamento);
        if(camp.getMax() <= Inscripcion_DAO.numeroAsistentes(camp.getId())){
            error=3;
            return false;
        }
         
        Registro tipoRegistro;
        
        int dias = (int) ChronoUnit.DAYS.between(fecha,camp.getInicio());
        if (dias>=15)
        {
            tipoRegistro = Registro.Temprano;
        }
        else if (dias>2)
        {
            tipoRegistro = Registro.Tardio;
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            error = 4;
            return false;
        }
        
        
        float precio;
        if(tipo==TipoInscripcion.Parcial){
            precio = 100 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }
        else{
            precio = 300 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }
        
        
        inscripcion =  new InscripcionDTO(idAsistente, idCampamento, fecha, precio, tipoRegistro, tipo);
        
        
        if(Inscripcion_DAO.existeInscripcion(idAsistente, idCampamento))
        {
            error = 5;
            return false;
        }
        else
        {
            Inscripcion_DAO.agregarInscripcion(inscripcion);
            error = -1;
            return true;
        }
    }

    public float precioInscripcion(int idCampamento, TipoInscripcion tipo){
        float precio;
        if(tipo==TipoInscripcion.Parcial){
            precio = 100 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }
        else{
            precio = 300 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }
        return precio;
    }

    public Registro tipoRegistro(int idCampamento){
        Registro tipoRegistro = null;

        CampamentoDTO camp = Campamento_DAO.buscarCampamento(idCampamento);
        int dias = (int) ChronoUnit.DAYS.between(LocalDate.now(), camp.getInicio());
        if (dias>=15)
        {
            tipoRegistro = Registro.Temprano;
        }
        else if (dias>2)
        {
            tipoRegistro = Registro.Tardio;
        }
        return tipoRegistro;
    }

    public boolean cancelarInscripcion(int idAsistente, int idCampamento)
    {
        InscripcionDTO inscripcion = Inscripcion_DAO.buscarInscripcion(idAsistente, idCampamento);
        if (inscripcion == null)
        {
            error = 2;
            return false;
        }

        if ( Campamento_DAO.buscarCampamento(inscripcion.getIdCampamento()).getInicio().isAfter(LocalDate.now()) )
        {
            if (inscripcion.getRegistro().equals(Registro.Temprano))
            {
                Inscripcion_DAO.borrar(idCampamento, idAsistente);
                return true;
            }
            else
            {
                error = 7;
                return false;
            }
        }
        else
        {
            error = 6;
            return false;
        }
    }
    
    /**
     * Metodo que lista los campamentos de los que se dispone.
     * @return Un ArrayList con la lista de Campamentos que hay en la bbdd
     */

    public ArrayList<CampamentoDTO> campamentosDisponibles()
    {
        LocalDate fecha = LocalDate.now();
        ArrayList<CampamentoDTO> camps = new ArrayList<CampamentoDTO>();

        camps = Campamento_DAO.buscarCampamentosPorFecha(fecha.plusDays(2));

        for (int i = 0; i < camps.size(); i++)
        {
            if (camps.get(i).getMax() <= Inscripcion_DAO.numeroAsistentes(camps.get(i).getId())){   

                camps.remove(i);
            }   
        }
    
        return camps;
    }

    /**
     * Metodo para traducir el codigo de error que ha podido ocurrir en los anteriores metodos
     * @return un String interpretable para la capa de visualizacion con el codigo del error
     */

    public String mensajeError()
    {
        String mensaje = new String();
        switch(error)
        {
            case -1:
                mensaje = "Inscripcion realizada con exito";
            break;
            
            case 0:
                mensaje = "Error. No Existe el asistente";
            break;

            case 1:
                mensaje = "Error. No Existe el campamento";
            break;

            case 2:
                mensaje = "Error. No Existe la inscripcion";
            break;

            case 3:
                mensaje = "Error. El campamento esta completo";
            break;

            case 4:
                mensaje = "Error. Se ha cerrado el periodo de inscripcion";
            break;

            case 5:
                mensaje = "Error. El asistente esta inscrito en este campamento";
            break;

            case 6:
                mensaje = "Error. Ya ha comenzado el campamento";
            break;

            case 7:
                mensaje = "Error. No se puede cancelar en la modalidad Tardia";
            break;

        }
        return mensaje;
    }

}