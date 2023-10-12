package clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Campamento {


    
    //Declaracion de los atributos de la clase campamento
    private int id_;                                //Identificador del campamento 
    private Date inicio_;                           //Fecha inicio 
    private Date fin_;                              //Fecha Final 
    private NivelEducativo nivel_;                  //Nivel educativo
    private int Nmax_;                              //Numero maximo de participantes 
    private ArrayList<Actividad> ListaActividades_;     //Actividades del campamento
    private ArrayList<Monitor> Responsable_;            //Monitor/s del campamento

    //Constructores 

    //Constructor Vacio
    Campamento(){

    }

    //Constructor Parametrizado
    Campamento(int id, Date inicio, Date fin, NivelEducativo nivel, int Nmax){

        id_ = id;
        inicio_ = inicio;
        fin_ = fin;
        nivel_ = nivel;
        Nmax_ = Nmax;
    }

    //Getters
        public int getId(){

            return id_;
        }

    //No se si sera necesario pero aqui un get de las fechas juntas 
        public String getDates(){

            String Fechas = ("Inicio: " + inicio_ + "\nFinal: " + fin_);
            return Fechas;
        }

        public Date getInicio(){

            return inicio_;
        }

        public Date getFinal(){

            return fin_;
        }

        public NivelEducativo getNivel(){

            return nivel_;
        }

        public int getMax(){

            return Nmax_;
        }

        public ArrayList<Monitor> getResponsables(){
            
            return new ArrayList<Monitor> (Responsable_);
        }


        public ArrayList<Actividad> getActividades(){
            
            return new ArrayList<Actividad> (ListaActividades_);
        }

    //Setters

        public void setId(int id){

            id_ = id;
        }

        public void setInicio(Date inici){

            inicio_ = inici;
        }

        public void setFin(Date fin){

            fin_ = fin;
        }

        public void setNivel(NivelEducativo nivel){

            nivel_ = nivel;
        }

        public void setMaxi(int n){

            Nmax_ = n;
        }

    //Metodos

        //tostring: Imprime la informacion del campamento
        @Override 
        public String toString(){

            String InfoCampamento = "Identificador: " + id_ + "\nFecha de Inicio: " + inicio_ + "\nFecha de finalizacion: "
                                    + fin_ + "\nNivel educativo: " + nivel_ + "Maximo de participantes: " + Nmax_ + 
                                    "Actividades:\n";
            
            for (Actividad i : ListaActividades_) {

                InfoCampamento = InfoCampamento + i.toString() + "\n";    
            }
            
            InfoCampamento = InfoCampamento + "Monitores responsables:\n";

                        
            for (Monitor i : Responsable_) {

                InfoCampamento = InfoCampamento + i.toString() + "\n";    
            }

            return InfoCampamento;
        }

        //addActividad: Añade una actividad si esta es del mismo nivel que el campamento

        public boolean addActividad(Actividad Nueva){

            if(Nueva.GetNivel() == this.nivel_){

                for(Actividad i : ListaActividades_){

                    if(i.GetNombre() == Nueva.GetNombre()){
                        System.out.println("Ya existe una actividad con ese nombre\n");
                        return false;
                    }
                }

                ListaActividades_.add(Nueva);
                return true;

            }else{

                System.out.println("Nivel educativo no compatible\n");
                return false;
            }
        }

        //addMonitor: Añade un Monitor resposable del campamento

        public boolean addMonitor(Monitor Nuevo){

            for(Actividad i : ListaActividades_){

                ArrayList<Monitor> Opciones = i.GetMonitoresEncargados();

                for(Monitor j : Opciones){

                    if(j.getId() == Nuevo.getId()){

                        if(!Responsable_.isEmpty()){

                            Responsable_.set(0, Nuevo);
                            return true;
                        }else{
                        
                            Responsable_.add(0, Nuevo);
                            return true;
                        }
                    }
                }
            }
        
            return false;
        }

        //addMonitorEspecial: Añade un segundo monitor si esta capacitado para trato especial
        public boolean addMonitorEspecial(Monitor Nuevo){

            if(Nuevo.getAtencionEsp() == true && !Responsable_.isEmpty()){

                for(Actividad i : ListaActividades_){

                    ArrayList<Monitor> Opciones = i.GetMonitoresEncargados();

                    for(Monitor j : Opciones){

                        if(j.getId() == Nuevo.getId()){
                            System.out.println("Este monitor esta asignado a una actividad\n");
                            return false;
                        }
                    }
                }
                if(Responsable_.size() == 2){

                    Responsable_.set(1, Nuevo);
                    return true;
                }else{
               
                    Responsable_.add(1, Nuevo);
                    return true;
                }

            }
        
            return false;
        }
}