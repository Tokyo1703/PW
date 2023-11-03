package Negocio;

import java.util.ArrayList;

import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.Monitor;
import Negocio.DTO.NivelEducativo;

import java.time.LocalDate;

import clases.*;

public class gestorCampamentos {
    private ArrayList<Actividad> Actividades;
    private ArrayList<Monitor> Monitores;
    private ArrayList<Campamento> campamentos;

    public boolean CargarActividades(String path){
        
    }
    public boolean CargarMonitores(String path);
    public boolean CargarCampamentos(String path);
    public gestorCampamentos(){
    }

    public gestorCampamentos(String ActividadesPath, String MonitoresPath, String CampamentoPath){
        CargarActividades(ActividadesPath);
        CargarMonitores(MonitoresPath);
        CargarCampamentos(CampamentoPath);
    }
    
    
    public boolean Crearcampamento(int id, LocalDate Fini, LocalDate Ffin, NivelEducativo Nivel, int Nmax){
        for( Campamento i : campamentos){
            if(id == i.getId()){
                System.out.println("Ya hay un campamento con ese id");
                return false;
            }
        }
        campamentos.add(new Campamento(id, Fini, Ffin,Nivel,Nmax));
        return true;
    }

    public boolean CrearMonitor(int id, String Nom, boolean Aten){
        for( Monitor i : Monitores){
            if(id == i.getId()){
                System.out.println("Ya hay un monitor con ese id");
                return false;
            }
        }
        Monitores.add(new Monitor(id, Nom, Aten));
        return true;
    }

    public boolean CrearActividad(String Nom, NivelEducativo Nivel, Horario Hora, int Capacidad, int MonitoresMax){
        for( Actividad i : Actividades){
            if(Nom == i.GetNombre()){
                System.out.println("Ya hay una actividad con ese nombre");
                return false;
            }
        }
        Actividades.add(new Actividad(Nom, Nivel, Hora, Capacidad, MonitoresMax));
        return true;
    }

    public Campamento BuscarCampId(int id){
        Campamento aux = new Campamento();
        for (Campamento i: campamentos)
            if(i.getId()==id)
                aux = i;
        
        return aux;
    }

    public boolean AsociarMonitorActividad(int id, String Nom ){
        Monitor auxM = new Monitor();
        boolean asociado = false, encotrado = false;
        for(Monitor i: Monitores){
            if (i.getId() == id){
                auxM = i;
                encotrado = true;
            }

        }
        if(encotrado)
            for(Actividad i : Actividades){
                if(i.GetNombre() == Nom){
                    i.asociarMonitor(auxM);
                    asociado=true;
                }
            }
        return asociado;
    }

    public boolean AsociarActividadcampamento(String Nom, int id ){
        Actividad auxA = new Actividad();
        boolean asociado = false, encotrado = false;
        for(Actividad i: Actividades){
            if (i.GetNombre() == Nom){
                auxA = i;
                encotrado = true;
            }

        }
        if(encotrado)
            for(Campamento i : campamentos){
                if(i.getId() == id){
                    i.addActividad(auxA);
                    asociado=true;
                }
            }
        return asociado;
    }

    public boolean AsociarMonitorCampamento(int IdMonitor, int IdCampamento ){
        boolean encotrado = false;
        int i=0;
        Monitor auxM = new Monitor();
        ArrayList<Actividad> aux = new ArrayList<Actividad>(BuscarCampId(IdCampamento).getActividades());
        while(encotrado == false && aux.size()>i+1){
            for(Monitor m : aux.get(i).GetMonitoresEncargados()){
                if(m.getId() == IdMonitor){
                    auxM = m;
                    encotrado = true;
                }
            }
            i++;
        }
        if (encotrado){
            BuscarCampId(IdCampamento).addMonitor(auxM);
            return true;
        } else 
            return false;
    }

    public boolean  AsociarMonitorEspecial(int IdMonitor, int IdCampamento){
        int i=0;
        boolean encontrado=false, AsistenteEspecial=false;
        

        while(i+1<Monitores.size() && encontrado==false){
            if(Monitores.get(i).getId()==IdMonitor){
                encontrado=true;
                auxM=Monitores.get(i);
            } else {
                i++;
            }
        }
        if(encontrado){
            BuscarCampId(IdCampamento).addMonitorEspecial(auxM);
            return true;
        } else {
            return false;
        }
    }
        
}

    