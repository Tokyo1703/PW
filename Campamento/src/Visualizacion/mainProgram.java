package Visualizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;

import Negocio.gestorAsistentes;
import Negocio.gestorInscripciones;
import Negocio.DTO.Asistente;

public class mainProgram {
    public static void main(String[] args){


        Properties prop = new Properties();
		String filename = "propiedades.properties";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			
            String Asistentes = prop.getProperty("asistentes");
            String Campamentos = prop.getProperty("campamentos");
            String Inscripciones = prop.getProperty("inscripciones");
			



        int opcion=0;
        int opcion2=0;
        Scanner sc;

        while(opcion!=4){

            System.out.println("\nEscoga una opción:\n"+
                    "1) Gestionar asistentes\n"+
                    "2) Gestionar campamentos\n"+
                    "3) Gestionar inscripciones\n"+
                    "4) Cerrar menu");



            sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch(opcion){
                
                case 1:
                    int id;
                    String nombre,aux;
                    LocalDate fecha;
                    Boolean atencion;
                    

                    gestorAsistentes gestorA=new gestorAsistentes(Asistentes);
                    while(opcion2!=4){
                        
                        System.out.println("\nGestor de asistentes:\n"+
                                    "1) Dar de alta a un asistente\n"+
                                    "2) Modificar la información de un asistente\n"+
                                    "3) Listar todos los asistentes\n"+
                                    "4) Cerrar gestor");

                        sc = new Scanner(System.in);
                        opcion2 = sc.nextInt();

                        switch(opcion2){
                            case 1:
                                
                                System.out.println("Introduzca el id:");
                                sc = new Scanner(System.in);
                                id=sc.nextInt();

                                System.out.println("Introduzca el nombre completo:");
                                sc = new Scanner(System.in);
                                nombre=sc.next();

                                System.out.println("Introduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
                                sc = new Scanner(System.in);
                                fecha = LocalDate.parse(sc.next());
                                
                                System.out.println("Si necesita atencion especial escriba 'si':");
                                sc = new Scanner(System.in);
                                aux=sc.next();
                                atencion=false;
                                if(aux=="si"){
                                    atencion=true;
                                }

                                Asistente asistente=new Asistente(id,nombre,fecha,atencion);
                                gestorA.addAsist(asistente);

                                break;
                            case 2:
                                System.out.println("Introduzca los nuevos datos del asitente");
                                System.out.println("Introduzca el id:");
                                sc = new Scanner(System.in);
                                id=sc.nextInt();

                                System.out.println("Introduzca el nombre completo:");
                                sc = new Scanner(System.in);
                                nombre=sc.next();

                                System.out.println("Introduzca la fecha de nacimiento con el siguiente formato 'yyyy-mm-dd':");
                                sc = new Scanner(System.in);
                                fecha = LocalDate.parse(sc.next());
                                
                                System.out.println("Si necesita atencion especial escriba 'si':");
                                sc = new Scanner(System.in);
                                aux=sc.next();
                                atencion=false;
                                if(aux=="si"){
                                    atencion=true;
                                }

                                Asistente asistenteM=new Asistente(id,nombre,fecha,atencion);
                                gestorA.editAsist(opcion2, asistenteM);
                                break;
                            case 3:
                                gestorA.print();
                                break;
                            case 4:
                                System.out.println("Saliendo...\n");
                                break;
                            default:
                                System.out.println("Opción no valida");
                        }

                    }
                    break;

                case 2:
                    while(opcion2!=8){
                        
                        System.out.println("\nGestor de campamentos:\n"+
                                    "1) Crear actividad\n"+
                                    "2) Crear monitor\n"+
                                    "3) Crear campamento\n"+
                                    "4) Asociar monitor a actividad\n"+
                                    "5) Asociar actividad a campamento\n"+
                                    "6) Asociar monitor a campamento\n"+
                                    "7) Asociar monitor de atención especial a campamento\n" +
                                    "8) Cerrar gestor");

                        sc = new Scanner(System.in);
                        opcion2 = sc.nextInt();

                        switch(opcion2){
                            case 1:
                                
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                System.out.println("Saliendo...\n");
                                break;
                            default:
                                System.out.println("Opción no valida");
                        }
                    }
                    break;

                case 3:
                    
                    gestorInscripciones gestor= new gestorInscripciones(Inscripciones,Campamentos,Asistentes);
                    int id_as,id_camp;
                    LocalDate fecha_camp;

                    while(opcion2!=4){
                        
                        System.out.println("\nGestor de inscripciones:\n"+
                                    "1) Realizar Inscripcion Completa \n"+
                                    "2) Realizar Inscripcion Parcial \n"+
                                    "3) Ver campamentos disponibles \n"+
                                    "4) Cerrar gestor"); 
                        sc = new Scanner(System.in);
                        opcion2 = sc.nextInt();
                        switch(opcion2){
                            case 1:
                                System.out.println("Introduzca el id del asistente:");
                                sc = new Scanner(System.in);
                                id_as=sc.nextInt();

                                System.out.println("Introduzca el id campamento:");
                                sc = new Scanner(System.in);
                                id_camp=sc.nextInt();

                                System.out.println("Introduzca la fecha actual con el siguiente formato 'yyyy-mm-dd':");
                                sc = new Scanner(System.in);
                                fecha_camp = LocalDate.parse(sc.next());

                                gestor.inscribirCompleta(id_as, id_camp, fecha_camp);
                                break;
                            case 2:
                                System.out.println("Introduzca el id del asistente:");
                                sc = new Scanner(System.in);
                                id_as=sc.nextInt();

                                System.out.println("Introduzca el id campamento:");
                                sc = new Scanner(System.in);
                                id_camp=sc.nextInt();

                                System.out.println("Introduzca la fecha actual con el siguiente formato 'yyyy-mm-dd':");
                                sc = new Scanner(System.in);
                                fecha = LocalDate.parse(sc.next());

                                gestor.inscribirParcial(id_as, id_camp, fecha);
                                break;
                            case 3:
                                System.out.println("Introduzca la fecha actual con el siguiente formato 'yyyy-mm-dd':");
                                sc = new Scanner(System.in);
                                fecha = LocalDate.parse(sc.next());
                                gestor.campamentos(fecha);
                                break;
                            case 4:
                                System.out.println("Saliendo...\n");
                                break;
                            default:
                                System.out.println("Opción no valida");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...\n");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}