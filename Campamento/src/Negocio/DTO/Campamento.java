package Negocio.DTO;

import java.time.LocalDate;

public class Campamento {


    
    //Declaracion de los atributos de la clase campamento
    private int id_;                                //Identificador del campamento 
    private LocalDate inicio_;                           //Fecha inicio 
    private LocalDate fin_;                              //Fecha Final 
    private NivelEducativo nivel_;                  //Nivel educativo
    private int Nmax_;                              //Numero maximo de participantes 
    private boolean AsistenteEspecial;

    //Constructores 

    //Constructor Vacio
    public Campamento(){

    }

    //Constructor Parametrizado
    public Campamento(int id, LocalDate inicio, LocalDate fin, NivelEducativo nivel, int Nmax){

        id_ = id;
        inicio_ = inicio;
        fin_ = fin;
        nivel_ = nivel;
        Nmax_ = Nmax;
        this.AsistenteEspecial = false;
    }

    //Getters
        public int getId(){

            return id_;
        }

    //No se si sera necesario pero aqui un get de las fechas juntas 
        public String getLocalDates(){

            String Fechas = ("Inicio: " + inicio_ + "\nFinal: " + fin_);
            return Fechas;
        }

        public LocalDate getInicio(){

            return inicio_;
        }

        public LocalDate getFinal(){

            return fin_;
        }

        public NivelEducativo getNivel(){

            return nivel_;
        }

        public int getMax(){

            return Nmax_;
        }

        public boolean getEspecial(){

            return AsistenteEspecial;
        }

    //Setters

        public void setId(int id){

            id_ = id;
        }

        public void setInicio(LocalDate inici){

            inicio_ = inici;
        }

        public void setFin(LocalDate fin){

            fin_ = fin;
        }

        public void setNivel(NivelEducativo nivel){

            nivel_ = nivel;
        }

        public void setMaxi(int n){

            Nmax_ = n;
        }

        public void setEspecial(boolean bol){

            AsistenteEspecial = bol;
        }

    //Metodos

        //tostring: Imprime la informacion del campamento
        @Override 
        public String toString(){

            String InfoCampamento = "Identificador: " + id_ + "\nFecha de Inicio: " + inicio_ + "\nFecha de finalizacion: "
                                    + fin_ + "\nNivel educativo: " + nivel_ + "Maximo de participantes: " + Nmax_ ;

            return InfoCampamento;
        }


}