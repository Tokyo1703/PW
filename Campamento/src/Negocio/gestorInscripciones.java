package Negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

import Datos.DAO.AsistenteDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.InscripcionDAO;

import Negocio.DTO.CampamentoDTO;
import Negocio.DTO.InscripcionDTO;
import Negocio.DTO.Enum.Registro;
import Negocio.DTO.Enum.TipoInscripcion;



public class gestorInscripciones
{
    // Codigo de error que se pueda producir, obten el mensaje con mensajeError()
    private int error;
    
    // Acceso a base de datos
    private InscripcionDAO Inscripcion_DAO=new InscripcionDAO(); 
    private CampamentoDAO Campamento_DAO=new CampamentoDAO();
    private AsistenteDAO Asistente_DAO=new AsistenteDAO();


    // Añadir Inscripciones

    public boolean realizarInscripcion(int idAsistente, int idCampamento, TipoInscripcion tipo){
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
            error=2;
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
            error = 3;
            return false;
        }
        
        
        float precio;
        if(tipo==TipoInscripcion.Parcial){
            precio = 100 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }
        else{
            precio = 300 + Campamento_DAO.numeroActividades(idCampamento)*20;
        }

        InscripcionDTO ins =  new InscripcionDTO(idAsistente, idCampamento, fecha, precio, tipoRegistro, tipo);
        
        if(Inscripcion_DAO.existeInscripcion(idAsistente, idCampamento))
        {
            error = 4;
            return false;
        }
        else
        {
            Inscripcion_DAO.agregarInscripcion(ins);
            error = -1;
            return true;
        }
    }

    
    // Campamentos disponibles

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
                mensaje = "Error. El campamento esta completo";
            break;

            case 3:
                mensaje = "Error. Se ha cerrado el periodo de inscripcion";
            break;

            case 4:
                mensaje = "Error. El asistente esta inscrito en este campamento";
            break;
        }
        return mensaje;
    }

}