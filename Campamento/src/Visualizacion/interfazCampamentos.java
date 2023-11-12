package Visualizacion;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Negocio.gestorCampamentos;
import Negocio.DTO.ActividadDTO;
import Negocio.DTO.CampamentoDTO;
import Negocio.DTO.MonitorDTO;
import Negocio.DTO.Enum.Horario;
import Negocio.DTO.Enum.NivelEducativo;

public class interfazCampamentos {
    
    private gestorCampamentos Gestor_campamento = new gestorCampamentos();

    public void menuGeneral(){
        try{
            int opcion = 0;
            Scanner sc;

            while(opcion != 4){
                
                System.out.println("\n---Menu Campamentos, Actividades y Monitores---\n\n"+
                                "1- Añadir monitor\n"+
                                "2- Gestionar actividades\n"+
                                "3- Gestionar campamentos\n"+
                                "4- Salir\n");

                sc = new Scanner(System.in);
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        
                        if(InsertarMonitor()){

                            System.out.println("\nMonitor añadido correctamente\n");
                        }else{

                            System.out.println("\nYa existe un monitor con ese ID\n");
                        }
                        break;
                    
                    case 2:

                        menuActividad();
                        break;
                    case 3:

                        menuCampamento();
                        break;
                    case 4:
                    
                        System.out.println("SALIENDO DEL MENU...\n");
                        break;
                    default:
                        
                        System.out.println("Error, opcion no valida\n");
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

    public void menuActividad(){
        try{
            int opcion = 0;
            Scanner sc;

            while(opcion != 3){

                System.out.println("\n---Menu Actividades---\n\n"+
                                "1- Añadir Actividad\n"+
                                "2- Vincular actividad con monitor\n"+
                                "3- Salir\n");
            
                sc = new Scanner(System.in);
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        
                        if(InsertarActividad()){

                            System.out.println("\nActividad añadida correctamente\n");
                        }else{

                            System.out.println("\nYa existe una actividad con ese nombre\n");
                        }
                        break;
                    case 2:
                        
                        Vincular_Monitor_Act();
                        break;
                    case 3:

                        System.out.println("SALIENDO MENU ACTIVIDADES...\n");
                        break;
                    default:

                        System.out.println("Error, opcion no valida\n");
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

    public void menuCampamento(){
        try{
            
            int opcion = 0;
            Scanner sc;
            
            while(opcion != 5){

                System.out.println("---MENU CAMPAMENTOS\n"+
                            "1- Añadir Campamento\n"+
                            "2- Vincular actividad a campamento\n"+
                            "3- Vincular Monitor Responsable\n"+
                            "4- Vincular un monitorESP\n"+
                            "5- Salir");

                sc = new Scanner(System.in);
                opcion = sc.nextInt();

                switch(opcion){

                    case 1:

                        if(InsertarCampamento()){

                            System.out.println("\nCampamento añadido correctamente\n");
                        }else{

                            System.out.println("\nYa existe un campamento con ese ID\n");
                        }
                        break;
                    case 2:
                        
                        Vincular_Act_Camp();
                        break;
                    case 3:

                        Vincular_MonitorResponsabe_Campamento();
                        break;                        
                    case 4:

                        Vincular_MonitorESP_Campamento();
                        break;
                    case 5:

                        System.out.println("SALIENDO MENU CAMPAMENTOS...\n");
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

    public boolean InsertarMonitor(){
        
        Scanner sc = new Scanner(System.in);
        int id;
        String nombre, aux;
        boolean especial;

        System.out.println("\nIntroduzca el id:");
        id = sc.nextInt();

        System.out.println("\nIntroduzca el nombre completo:");
        nombre = sc.next();
        nombre += sc.nextLine();

        System.out.println("\n¿Esta capacitado como asistente especial) si/no:");
        aux = sc.next();
        especial = false;

        if(aux.equals("si")){

            especial = true;
        }

        MonitorDTO nuevo = new MonitorDTO(id, nombre, especial);

        
        return Gestor_campamento.InsertarMonitor(nuevo);
    }

    public boolean InsertarActividad(){

        Scanner sc = new Scanner(System.in);
        String Nombre;
        NivelEducativo Nivel;
        Horario Hora;
        int Capacidad;
        int MonitoresMax;

        System.out.println("\nIntroduzca nombre de la actividad");
        Nombre = sc.nextLine();

        System.out.println("\nInserte nivel educativo de la actividad(Infantil, Juvenil, Adolescente)");
        Nivel = NivelEducativo.valueOf(sc.next());

        System.out.println("\nInserte horario (Manana/Tarde)");
        Hora = Horario.valueOf(sc.next());

        System.out.println("\nInserte la capacidad de la actividad");
        Capacidad = sc.nextInt();


        System.out.println("\nInserte el numero de monitores necesarios");
        MonitoresMax = sc.nextInt();

        ActividadDTO nueva = new ActividadDTO(Nombre, Nivel, Hora, Capacidad, MonitoresMax);

        
        return Gestor_campamento.InsertarActividad(nueva);
    }

    public void Vincular_Monitor_Act(){

        int ID_Monitor;
        String Nombre_Actividad;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInserte nombre de la actividad a la que va a vincular un monitor");
        Nombre_Actividad = sc.nextLine();

        System.out.println("\nInserte ID del Monitor que vincular a la actividad");
        ID_Monitor = sc.nextInt();

        switch(Gestor_campamento.AsociarMonitorActividad(ID_Monitor, Nombre_Actividad)){

            case 0:

                System.out.println("El monitor se asocio correctamente a la actividad\n");
                break;
            case 1:

                System.out.println("No existe ningun Monitor con ese ID\n"+
                            "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
            case 2:

                System.out.println("No existe ninguna actividad con ese nombre\n"+
                            "Vuelva a probar o asegurese de haber insertado correctamente el nombre\n");
                break;

            case 3:
                System.out.println("Este monitor ya esta asociado a esta actividad\n"+
                            "Vuelva a probar o asegurese de haber insertado correctamente los datos\n");
                break;
                
            case 4:

                System.out.println("Este campamento ya tiene todos los monitores necesarios\n");
                break;
        }
        
    }        


    public boolean InsertarCampamento(){
        
        int id, Max;
        LocalDate Inicio, Fin;
        NivelEducativo nivel;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInserte id del nuevo campamento");
        id = sc.nextInt();

        System.out.println("\nInserte la fecha de inicio del campamento (Formato yyyy-mm-dd)");
        Inicio = LocalDate.parse(sc.next());
    
        System.out.println("\nInserte fecha de finalizacion del campamento (Formato yyyy-mm-dd)");
        Fin = LocalDate.parse(sc.next());

        System.out.println("\nInserte nivel educativo del campamento (Infantil, Juvenil, Adolescente)");
        nivel = NivelEducativo.valueOf(sc.next());
        
        System.out.println("\nInserte capacidad maxima del campamento");
        Max = sc.nextInt();

        CampamentoDTO nuevo = new CampamentoDTO(id, Inicio, Fin, nivel, Max);

        
        return Gestor_campamento.InsertarCampamento(nuevo);
    }

    public void Vincular_Act_Camp(){

        int ID_Campamento;
        String Nombre_Actividad;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInserte nombre de la actividad que quiera vincular");
        Nombre_Actividad = sc.nextLine();

        System.out.println("\nInserte ID del campamento al que va a añadir dicha actividad");
        ID_Campamento = sc.nextInt();

        switch(Gestor_campamento.AsociarActividadcampamento(ID_Campamento, Nombre_Actividad)){

            case 0:

                System.out.println("La actividad se asocio correctamente al campamento\n");
                break;
            case 1:

                System.out.println("No existe ningun campamento con ese ID\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
            case 2:

                System.out.println("No existe ninguna actividad con ese nombre\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el nombre\n");
                break;

            case 3:
                System.out.println("Este actividad ya esta asociada a este campamento\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente los datos\n");
                break;

            case 4:
                System.out.println("El campamento y la actividad no son del mismo nivel educativo\n");
                break;

        }
        
    }

    public void Vincular_MonitorResponsabe_Campamento(){

        int ID_Campamento, ID_Monitor;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInserte el Id del Campamento");
        ID_Campamento = sc.nextInt();

        System.out.println("\nInserte el Id del monitor que sera el responsable del Campamento");
        ID_Monitor = sc.nextInt();

        switch (Gestor_campamento.AsociarMonitorResponsable(ID_Monitor, ID_Campamento)) {
            case 0:
                
                System.out.println("Monitor Responsable asociado correctamente\n");
                break;
        
            case 1:

                System.out.println("No existe ningun monitor con ese ID\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

            case 2:

                System.out.println("El monitor que quiere asignar no cumple con los requisitos: "+
                                "Estar en una actividad vinculada al campamento\n");
                break;

            case 3:
                System.out.println("No existe ningun campamento con ese ID\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

        }
        
    }

    public void Vincular_MonitorESP_Campamento(){

        int ID_Campamento, ID_Monitor;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInserte el Id del Campamento");
        ID_Campamento = sc.nextInt();

        System.out.println("\nInserte el Id del monitor especial que quiere vincular");
        ID_Monitor = sc.nextInt();


        switch (Gestor_campamento.AsociarMonitorESPCampamento(ID_Monitor, ID_Campamento)) {
            case 0:
                
                System.out.println("Monitor especial asociado correctamente\n");
                break;
        
            case 1:

                System.out.println("No existe ningun monitor con ese ID\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

            case 2:

                System.out.println("El monitor que quiere asignar no cumple con alguno de los requisitos:\n"+
                                "No estar en una actividad\n"+
                                "Ser un monitor con capacidad especial\n");
                break;

            case 3:
                System.out.println("No existe ningun campamento con ese ID\n"+
                                "Vuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
        }
        
    }
    
}