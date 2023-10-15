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
                        
                        System.out.println("Gestor de asistentes:\n"+
                                    "1) Dar de alta a un asistente\n"+
                                    "2) Modificar la información de un asistente\n"+
                                    "3) Listar todos los asistentes\n"+
                                    "4) Cerrar gestor\n");

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
                    while(opcion2!=8){
                        
                        System.out.println("Gestor de campamentos:\n"+
                                    "1) Crear actividad\n"+
                                    "2) Crear monitor\n"+
                                    "3) Crear campamento\n"+
                                    "4) Asociar monitor a actividad\n"+
                                    "5) Asociar actividad a campamento\n"+
                                    "6) Asociar monitor a campamento\n"+
                                    "7) Asociar monitor de atención especial a campamento\n" +
                                    "8) Cerrar gestor\n");

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
                        
                        System.out.println("Gestor de inscripciones:\n"+
                                    "1) \n"+
                                    "2) \n"+
                                    "3) \n"+
                                    "4) Cerrar gestor\n"); 
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