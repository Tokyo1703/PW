package Negocio;

import Negocio.DTO.AsistenteDTO;

import java.util.ArrayList;

import Datos.DAO.AsistenteDAO;
/**
 * Clase gestor asistentes, encargada de realizar las comprovaciones en los datos recividos de la capa de 
 * visualizacion antes de almacenarlos en la base de datos.
 */
public class gestorAsistentes{

    /**
     * Variable DAO, representa una instancia de la clase AsistenteDAO usada para la conexion con la base de datos.
     */

    private AsistenteDAO DAO;
    
    public gestorAsistentes(){

        DAO = new AsistenteDAO();
    }

    /**
     * Metodo para comprobar si exite un registro en la tabla Asistentes con una clave primaria id.
     * @param id clave primaria que se va a comprobar.
     * @return true si exite dicho id en la clave primaria, false si no.
     */

    public boolean existeID(int id){

        if(DAO.existeID(id)){

            return true;
        }

        return false;
    }

    /**
     * Metodo para insertar un nuevo Asistente en la base de datos si no existe previamente
     * @param Nuevo El nuevo asistente que se quiere insertar
     * @return true si se inserto correctamente en la bbdd, false si no
     */

    public boolean insertarAsistente(AsistenteDTO Nuevo){

        if(!DAO.existeID(Nuevo.getId())){

            DAO.AgregarAsistente(Nuevo);
            return true;
        }

        return false;
    }

    /**
     * Metodo para editar un asistente si esta en la bbdd
     * @param id el id del asistente que se quiere editar
     * @param Editado los nuevos datos del asistente
     * @return true si se edito correctamente en la bbdd, false si no
     */

    public boolean editarAsistente(int id, AsistenteDTO Editado){

        if(DAO.existeID(id)){

            DAO.modificar(Editado);
            return true;
        }

        return false;
    }

    /**
     * Metodo para listar los asistentes 
     * @return Un ArrayList de la clase AsistenteDTO con los asistentes de la bbdd
     */

    public ArrayList<AsistenteDTO> Listar(){

        ArrayList<AsistenteDTO> lista = new ArrayList<AsistenteDTO>(); 
        lista = DAO.listaAsistentes();

        return lista;
    }
}