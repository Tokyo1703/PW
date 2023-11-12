package Visualizacion;

import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args){

        interfazAsistentes InterfazAsistentes = new interfazAsistentes();
        interfazInscripciones InterfazInscripciones = new interfazInscripciones();
        interfazCampamentos InterfazCampamentos = new interfazCampamentos();

        int opcion = 0;

        Scanner sc;


        while (opcion != 4){

            System.out.println("\n---Menu Principal---\n\n"+
                    "1) Menu asistentes\n"+
                    "2) Menu campamentos, actividades y monitores\n"+
                    "3) Menu inscripciones\n"+
                    "4) Cerrar menu");

            sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch(opcion){
                
                case 1:

                    InterfazAsistentes.menu();
                    break;

                case 2:

                    InterfazCampamentos.menuGeneral();
                    break;

                case 3:

                    InterfazInscripciones.menu();
                    break;

                case 4:

                    System.out.println("CERRANDO MENU...\n");
                    break;
            
                default:

                    System.out.println("Opcion no valida\n");
                    break;
            }
        }
    }
}