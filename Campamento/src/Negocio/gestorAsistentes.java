package Negocio;

import Negocio.DTO.AsistenteDTO;

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


    public boolean insertarAsistente(AsistenteDTO Nuevo){

        if(!DAO.existeID(Nuevo.getId())){

            DAO.AgregarAsistente(Nuevo);
            return true;
        }

        return false;
    }

    public boolean editarAsistente(int id, AsistenteDTO Editado){

        if(DAO.existeID(id)){

            DAO.modificar(Editado);
            return true;
        }

        return false;
    }

    public ArrayList<AsistenteDTO> Listar(){

        ArrayList<AsistenteDTO> lista = new ArrayList<AsistenteDTO>(); 
        lista = DAO.listaAsistentes();

        return lista;
    }
}