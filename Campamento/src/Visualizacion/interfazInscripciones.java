package Visualizacion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Negocio.gestorInscripciones;
import Negocio.DTO.Asistente;
import Negocio.DTO.Campamento;

public class interfazInscripciones {
    private gestorInscripciones Gestor = new gestorInscripciones();

    public void menu(){

        try{

            Scanner sc=new Scanner(System.in);
            int opcion=0;

            while(opcion!=4){

                System.out.println("---Menu Inscripciones---\n\n"+
                                "1- Realizar Inscripcion Completa \n"+
                                "2- Realizar Inscripcion Parcial \n"+
                                "3- Ver campamentos disponibles \n"+
                                "4- Cerrar gestor"); 

                opcion = sc.nextInt();
            
                switch (opcion) {
                    case 1:

                        if(!inscripcionCompleta()){
                            System.out.println(Gestor.mensajeError());
                        }

                        break;

                    case 2:

                        if(!inscripcionParcial()){
                            System.out.println(Gestor.mensajeError());
                        }
                        
                        break;

                    case 3:

                        ListarCampamentos();
                        break;

                    case 4:
                        
                        System.out.println("SALIENDO GESTOR INSCRIPCIONES...");
                        
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

    private boolean inscripcionCompleta(){

        Scanner sc=new Scanner(System.in);

        System.out.println("\nIntroduzca el id del asistente");
        int idAsistente=sc.nextInt();

        System.out.println("\nIntroduzca el id del campamento");
        int idCampamento=sc.nextInt();

        sc.close();
        return Gestor.inscribirCompleta(idAsistente, idCampamento);
    }

    private boolean inscripcionParcial(){

        Scanner sc=new Scanner(System.in);

        System.out.println("\nIntroduzca el id del asistente");
        int idAsistente=sc.nextInt();

        System.out.println("\nIntroduzca el id del campamento");
        int idCampamento=sc.nextInt();

        sc.close();
        return Gestor.inscribirParcial(idAsistente, idCampamento);
    }

    private void ListarCampamentos(){

        ArrayList<Campamento> lista = new ArrayList<Campamento>();
        lista = Gestor.campamentosDisponibles();

        for(Campamento it: lista){

            System.out.println(it.toString());
        }
    }
}
