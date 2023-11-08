package Visualizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Negocio.gestorAsistentes;
import Negocio.DTO.Asistente;

public class interfazAsistentes {

    private gestorAsistentes Gestor = new gestorAsistentes();

    public void menu(){

        System.out.println("---Menu Asistentes---\n\n1- Añadir nuevo asistente\n2- Modificar un asistente\n3- Borrar un asistente\n4- Listar asistentes\n5- salir");
    }

    public boolean añadir(){

        Scanner sc;
        int id;
        String nombre, aux;
        LocalDate fecha;
        boolean especial;

        System.out.println("Introduzca el id:");
        sc = new Scanner(System.in);
        id = sc.nextInt();
        sc.close();


        System.out.println("Introduzca el nombre completo:");
        sc = new Scanner(System.in);
        nombre = sc.next();
        sc.close();

        System.out.println("Introduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
        sc = new Scanner(System.in);
        fecha = LocalDate.parse(sc.next());
        sc.close();

        System.out.println("¿ Necesita atencion especial ? si/no :");
        sc = new Scanner(System.in);
        aux = sc.next();
        sc.close();
        especial = false;

        if (aux == "si") {

            especial = true;
        }

        Asistente nuevo = new Asistente(id, nombre, fecha, especial);
        
        return Gestor.insertarAsistente(nuevo);
    }

    public boolean editar(){

        Scanner sc;
        int id;
        String nombre, aux;
        LocalDate fecha;
        boolean especial;

        System.out.println("Inserte el id del asistente que desea editar\n");
        sc = new Scanner(System.in);
        id = sc.nextInt();
        sc.close();

        if(!Gestor.existeID(id)){

            System.out.println("No existe un asistente con ese ID\n");
            return false;
        }

        System.out.println("Introduzca el nombre completo:");
        sc = new Scanner(System.in);
        nombre = sc.next();
        sc.close();

        System.out.println("Introduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
        sc = new Scanner(System.in);
        fecha = LocalDate.parse(sc.next());
        sc.close();

        System.out.println("¿ Necesita atencion especial ? si/no :");
        sc = new Scanner(System.in);
        aux = sc.next();
        sc.close();
        especial = false;

        if (aux == "si") {

            especial = true;
        }

        Asistente edit = new Asistente(id, nombre, fecha, especial);
        return Gestor.editarAsistente(id, edit);
    }

    public void ListarAsistentes(){

        ArrayList<Asistente> lista = new ArrayList<Asistente>();
        lista = Gestor.Listar();

        for(Asistente it: lista){

            System.out.println(it.toString());
        }
    }
}
