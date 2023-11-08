package Negocio;

import Negocio.DTO.Asistente;

import java.util.ArrayList;

import Datos.DAO.AsistenteDAO;

public class gestorAsistentes{

    private AsistenteDAO DAO;
    
    public gestorAsistentes(){

        DAO = new AsistenteDAO();
    }

    public boolean existeID(int id){

        if(DAO.existeID(id)){

            return true;
        }

        return false;
    }


    public boolean insertarAsistente(Asistente Nuevo){

        if(!DAO.existeID(Nuevo.getId())){

            DAO.AgregarAsistente(Nuevo);
            return true;
        }

        return false;
    }

    public boolean editarAsistente(int id, Asistente Editado){

        if(DAO.existeID(id)){

            DAO.modificar(Editado);
            return true;
        }

        return false;
    }

    public ArrayList<Asistente> Listar(){

        ArrayList<Asistente> lista = new ArrayList<Asistente>(); 
        lista = DAO.listaAsistentes();

        return lista;
    }
}