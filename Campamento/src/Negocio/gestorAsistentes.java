package Negocio;

import Negocio.DTO.Asistente;

import Datos.DAO.AsistenteDAO;

public class gestorAsistentes{

    private AsistenteDAO DAO;
    
    public gestorAsistentes(){

        DAO = new AsistenteDAO();
    }


    public boolean addAsist(Asistente Nuevo){

        if(!DAO.existeID(Nuevo.getId())){

            DAO.AgregarAsistente(Nuevo);
            return true;
        }else{

            System.out.println("Ese asistente ya esta registrado\n");
        }

        return false;
    }

    public boolean editAsist(int id, Asistente Editado){

        if(DAO.existeID(id)){

            DAO.modificar(Editado);
            return true;
        }else{

            System.out.println("Asistente no encontrado\n");
        }

        return false;
    }

    public boolean deleteAsist(int id){

        if(DAO.existeID(id)){

            //Llamada al DAO.Delete(int id)
            return true;
        }else{

            System.out.println("Asistente no encontrado\n");
        }

        return false;
    }

    public boolean deleteAsist(Asistente Asist){

        int id = Asist.getId();

        return deleteAsist(id);
    }

    public void Listar(){

        DAO.listaAsistentes();
    }
}