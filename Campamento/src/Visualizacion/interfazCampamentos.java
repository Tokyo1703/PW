package Visualizacion;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Negocio.gestorCampamentos;
import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.Monitor;
import Negocio.DTO.NivelEducativo;

public class interfazCampamentos {
    
    private gestorCampamentos Gestor_campamento = new gestorCampamentos();

    public void menuGeneral(){
        try{
            int opcion = 0;
            Scanner sc;

            while(opcion != 4){
                
                System.out.println("---Menu Campamentos---\n1- Añadir Monitor\n2- Gestionar Actividades\n3- Gestionar Campamentos\n4- Salir\n");

                sc = new Scanner(System.in);
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        
                        if(InsertarMonitor()){

                            System.out.println("Monitor añadido correctamente\n");
                        }else{

                            System.out.println("Ya existe un monitor con ese ID\n");
                        }
                        break;
                    
                    case 2:

                        menuActividad();
                        break;
                    case 3:

                        menuCampamento();
                        break;
                    case 4:
                    
                        System.out.println("SALIENDO MENU CAMPAMENTO...\n");
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

                System.out.println("---GESTOR ACTIVIDADES---\n1- Añadir Actividad\n2- Vincular actividad con monitor\n3- Salir\n");
            
                sc = new Scanner(System.in);
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        
                        if(InsertarActividad()){

                            System.out.println("Actividad añadida correctamente\n");
                        }else{

                            System.out.println("Ya existe una actividad con ese nombre\n");
                        }
                        break;
                    case 2:
                        
                        Vincular_Monitor_Act();
                        break;
                    case 3:

                        System.out.println("SALIENDO GESTOR ACTIVIDADES...\n");
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

                System.out.println("---GESTOR CAMPAMENTOS\n1- Añadir Campamento\n2- Vincular actividad a campamento\n3- Vincular Monitor Responsable\n4- Vincular un monitorESP\n5- Salir");

                sc = new Scanner(System.in);
                opcion = sc.nextInt();

                switch(opcion){

                    case 1:

                        if(InsertarCampamento()){

                            System.out.println("Campamento añadido correctamente\n");
                        }else{

                            System.out.println("Ya existe un campamento con ese ID\n");
                        }
                        break;
                    case 2:
                        
                        Vincular_Act_Camp();
                        break;
                    case 3:

                        Vincular_MonitroResponsabe_Campamento();
                        break;                        
                    case 4:

                        Vincular_MonitorESP_Campamento();
                        break;
                    case 5:

                        System.out.println("SALIENDO GESTOR CAMPAMENTOS...\n");
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

        System.out.println("Introduzca el id:\n");
        id = sc.nextInt();

        System.out.println("Introduzca el nombre completo:\n");
        nombre = sc.next();
        nombre += sc.nextLine();

        System.out.println("¿Esta capacitado como asistente especial) si/no:\n");
        aux = sc.next();
        especial = false;

        if(aux.equals("si")){

            especial = true;
        }

        Monitor nuevo = new Monitor(id, nombre, especial);

        return Gestor_campamento.InsertarMonitor(nuevo);
    }

    public boolean InsertarActividad(){

        Scanner sc = new Scanner(System.in);
        String Nombre, aux;
        NivelEducativo Nivel;
        Horario Hora;
        int Capacidad;
        int MonitoresMax;

        System.out.println("Introduzca nombre de la actividad\n");
        Nombre = sc.nextLine();

        System.out.println("Inserte nivel educativo de la actividad(Infantil, Juvenil, Adolescente)\n");
        aux = sc.next();
        if(aux.equals("Infantil")){

            Nivel = NivelEducativo.Infantil;
        }else if(aux.equals("Juvenil")){

            Nivel = NivelEducativo.Juvenil;
        }else{

            Nivel = NivelEducativo.Adolescente;
        }

        System.out.println("Inserte horario (Mañana/Tarde)\n");
        aux = sc.next();
        if(aux.equals("Tarde")){

            Hora = Horario.Tarde;
        }else{

            Hora = Horario.Manana;
        }

        System.out.println("Inserte la capacidad de la actividad\n");
        Capacidad = sc.nextInt();


        System.out.println("Inserte la capacidad maxima de monitores de la actividad\n");
        MonitoresMax = sc.nextInt();

        Actividad nueva = new Actividad(Nombre, Nivel, Hora, Capacidad, MonitoresMax);

        return Gestor_campamento.InsertarActividad(nueva);
    }

    public void Vincular_Monitor_Act(){

        int ID_Monitor;
        String Nombre_Actividad;
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte nombre de la actividad a la que va a vincular un monitor\n");
        Nombre_Actividad = sc.nextLine();

        System.out.println("Inserte ID del Monitor que vincular a la actividad\n");
        ID_Monitor = sc.nextInt();

        switch(Gestor_campamento.AsociarMonitorActividad(ID_Monitor, Nombre_Actividad)){

            case 0:

                System.out.println("El monitor se asocio correctamente a la actividad\n");
                break;
            case 1:

                System.out.println("No existe ningun Monitor con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
            case 2:

                System.out.println("No existe ninguna actividad con ese nombre\nVuelva a probar o asegurese de haber insertado correctamente el nombre\n");
                break;
        }
    }        


    public boolean InsertarCampamento(){
        
        int id, Max;
        LocalDate Inicio, Fin;
        NivelEducativo nivel;
        Scanner sc = new Scanner(System.in);
        String aux;

        System.out.println("Inserte id del nuevo campamento\n");
        id = sc.nextInt();

        System.out.println("Inserte la fecha de inicio del campamento (Formato yyyy-mm-dd)\n");
        Inicio = LocalDate.parse(sc.next());
    
        System.out.println("Inserte fecha de finalizacion del campamento (Formato yyyy-mm-dd)\n");
        Fin = LocalDate.parse(sc.next());

        System.out.println("Inserte nivel educativo del campamento (Infantil, Juvenil, Adolescente)\n");
        aux = sc.next();
        if(aux.equals("Infantil")){

            nivel = NivelEducativo.Infantil;
        }else if(aux.equals("Juvenil")){

            nivel = NivelEducativo.Juvenil;
        }else{

            nivel = NivelEducativo.Adolescente;
        }

        System.out.println("Inserte capacidad maxima del campamento\n");
        Max = sc.nextInt();

        Campamento nuevo = new Campamento(id, Inicio, Fin, nivel, Max);

        return Gestor_campamento.InsertarCampamento(nuevo);
    }

    public void Vincular_Act_Camp(){

        int ID_Campamento;
        String Nombre_Actividad;
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte nombre de la actividad que quiera vincular\n");
        Nombre_Actividad = sc.nextLine();

        System.out.println("Inserte ID del campamento al que va a añadir dicha actividad\n");
        ID_Campamento = sc.nextInt();

        switch(Gestor_campamento.AsociarActividadcampamento(ID_Campamento, Nombre_Actividad)){

            case 0:

                System.out.println("La actividad se asocio correctamente al campamento\n");
                break;
            case 1:

                System.out.println("No existe ningun campamento con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
            case 2:

                System.out.println("No existe ninguna actividad con ese nombre\nVuelva a probar o asegurese de haber insertado correctamente el nombre\n");
                break;
        }
    }

    public void Vincular_MonitroResponsabe_Campamento(){

        int ID_Campamento, ID_Monitor;
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte el Id del Campamento\n");
        ID_Campamento = sc.nextInt();

        System.out.println("Inserte el Id del monitor que sera el responsabre del Campamento\n");
        ID_Monitor = sc.nextInt();

        switch (Gestor_campamento.AsociarMonitorResponsable(ID_Monitor, ID_Campamento)) {
            case 0:
                
                System.out.println("Monitor Responsable asociado correctamente\n");
                break;
        
            case 1:

                System.out.println("No existe ningun monitor con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

            case 2:

                System.out.println("El monitor que quiere asignar no cumple con los requisitos: Estar en una actividad vinculada al campamento\n");
                break;

            case 3:
                System.out.println("No existe ningun campamento con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

        }
    }

    public void Vincular_MonitorESP_Campamento(){

        int ID_Campamento, ID_Monitor;
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte el Id del Campamento\n");
        ID_Campamento = sc.nextInt();

        System.out.println("Inserte el Id del monitor especial que quiere vincular\n");
        ID_Monitor = sc.nextInt();


        switch (Gestor_campamento.AsociarMonitorESPCampamento(ID_Monitor, ID_Campamento)) {
            case 0:
                
                System.out.println("Monitor especial asociado correctamente\n");
                break;
        
            case 1:

                System.out.println("No existe ningun monitor con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;

            case 2:

                System.out.println("El monitor que quiere asignar no cumple con alguno de los requisitos:\nNo estar en una actividad\nSer un monitor con capacidad especial\n");
                break;

            case 3:
                System.out.println("No existe ningun campamento con ese ID\nVuelva a probar o asegurese de haber insertado correctamente el ID\n");
                break;
        }
    }
}