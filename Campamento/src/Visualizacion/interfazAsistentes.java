package Visualizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Negocio.gestorAsistentes;
import Negocio.DTO.Asistente;

public class interfazAsistentes {

    private gestorAsistentes Gestor = new gestorAsistentes();

    public void menu(){
        try{
            Scanner sc;
            int opcion=0;
            while(opcion!=4){

                System.out.println("---Menu Asistentes---\n\n"+
                                "1- Añadir nuevo asistente\n"+
                                "2- Modificar un asistente\n"+
                                "3- Listar asistentes\n"+
                                "4- Salir");

                sc = new Scanner(System.in);
                opcion = sc.nextInt();
            
            
                switch (opcion) {
                    case 1:

                        if(añadir()){

                            System.out.println("Asistente Añadido correctamente\n");
                        }else{

                            System.out.println("Error. El asistente ya existe\n");
                        }
                        break;

                    case 2:

                        if(editar()){

                            System.out.println("Asistente modificado con exito\n");
                        }else{

                            System.out.println("Error. No se encontro dicho asistente\n");
                        }

                        break;

                    case 3:

                        ListarAsistentes();

                        break;
                    case 4:
                        
                        System.out.println("SALIENDO GESTOR ASISTENTES...");
                        
                        break;

                    default:
                        System.out.println("Opcion no valida\n");
                        break;
                }
            }                
        }
        catch(InputMismatchException e){
            e.printStackTrace();
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
        catch(IllegalStateException e){
            e.printStackTrace();
        }
        
    }

    private boolean añadir(){

        Scanner sc = new Scanner(System.in);
        int id;
        String nombre="", aux;
        LocalDate fecha;
        boolean especial;

        System.out.println("\nIntroduzca el id:");
        id = sc.nextInt();

        System.out.println("\nIntroduzca el nombre completo:");
        sc.nextLine();
        nombre = sc.nextLine();

        System.out.println("\nIntroduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
        fecha = LocalDate.parse(sc.next());

        System.out.println("\n¿Necesita atencion especial? si/no :");
        aux = sc.next();
        especial = false;

        if(aux.equals("si")){

            especial = true;
        }

        Asistente nuevo = new Asistente(id, nombre, fecha, especial);

        return Gestor.insertarAsistente(nuevo);
        
    }

    private boolean editar(){

        Scanner sc=new Scanner(System.in);
        int id;
        String nombre, aux;
        LocalDate fecha;
        boolean especial;

        System.out.println("\nInserte el id del asistente que desea editar");
        id = sc.nextInt();

        if(!Gestor.existeID(id)){

            System.out.println("\nNo existe un asistente con ese ID");
            return false;
        }

        System.out.println("\nIntroduzca el nombre completo:");
        sc.nextLine();
        nombre = sc.nextLine();

        System.out.println("\nIntroduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
        fecha = LocalDate.parse(sc.next());

        System.out.println("\n ¿Necesita atencion especial? si/no :");
        aux = sc.next();
        especial = false;

        if (aux.equals("si")) {

            especial = true;
        }

        Asistente edit = new Asistente(id, nombre, fecha, especial);
        return Gestor.editarAsistente(id, edit);
    }

    private void ListarAsistentes(){

        ArrayList<Asistente> lista = new ArrayList<Asistente>();
        lista = Gestor.Listar();

        for(Asistente it: lista){

            System.out.println(it.toString());
        }
    }
}
