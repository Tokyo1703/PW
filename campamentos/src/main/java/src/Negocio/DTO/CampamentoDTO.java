package src.Negocio.DTO;

import java.time.LocalDate;

import src.Negocio.DTO.Enum.NivelEducativo;

public class CampamentoDTO {


    
    //Declaracion de los atributos de la clase campamento
    private int id_;                                //Identificador del campamento 
    private LocalDate inicio_;                           //Fecha inicio 
    private LocalDate fin_;                              //Fecha Final 
    private NivelEducativo nivel_;                  //Nivel educativo
    private int Nmax_;                              //Numero maximo de participantes 
    private String monitorResponsable_;
    private String monitorEspecial_;

    //Constructores 

    //Constructor Vacio
    public CampamentoDTO(){

    }

    //Constructor Parametrizado
    public CampamentoDTO(int id, LocalDate inicio, LocalDate fin, NivelEducativo nivel, int Nmax){

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

        public String getResponsable(){

            return monitorResponsable_;
        }

        public String getEspecial(){

            return monitorEspecial_;
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

        public void setResponsable(String monitorResponsable){

            monitorResponsable_=monitorResponsable;
        }

        public void setEspecial(String monitorEspecial){

            monitorEspecial_ = monitorEspecial;
        }

    //Metodos

        //tostring: Imprime la informacion del campamento
        @Override 
        public String toString(){

            String InfoCampamento = "\nIdentificador: " + id_ + "\nFecha de Inicio: " + inicio_ + "\nFecha de finalizacion: "
                                    + fin_ + "\nNivel educativo: " + nivel_ + "\nMaximo de participantes: " + Nmax_ ;

            return InfoCampamento;
        }


}