package mainProgram;


import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args){

        int opcion=0;
        int opcion2=0;
        Scanner sc;

        while(opcion!=4){

            System.out.println("Escoga una opción:\n"+
                    "1) Gestionar asistentes\n"+
                    "2) Gestionar campamentos\n"+
                    "3) Gestionar inscripciones\n"+
                    "4) Cerrar menu\n");



            sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch(opcion){

                case 1:
                    while(opcion2!=4){
                        
                        System.out.println("Escoga una opción:\n"+
                                    "1) Dar de alta a un asistente\n"+
                                    "2) Modificar la información de un asistente\n"+
                                    "3) Listar todos los asistentes\n"+
                                    "4) Cerrar menu\n");

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

                case 2:
                    while(opcion2!=4){
                        
                        System.out.println("Escoga una opción:\n"+
                                    "1) Dar de alta a un asistente\n"+
                                    "2) Modificar la información de un asistente\n"+
                                    "3) Listar todos los asistentes\n"+
                                    "4) Cerrar menu\n");

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

                    while(opcion2!=4){
                        
                        System.out.println("Escoga una opción:\n"+
                                    "1) Dar de alta a un asistente\n"+
                                    "2) Modificar la información de un asistente\n"+
                                    "3) Listar todos los asistentes\n"+
                                    "4) Cerrar menu\n"); 
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

                case 4:
                    System.out.println("Saliendo...\n");
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    }
}