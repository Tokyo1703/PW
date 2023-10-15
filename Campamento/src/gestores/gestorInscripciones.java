package gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import clases.Asistente;
import clases.Campamento;
import clases.InscripcionCompleta;
import clases.InscripcionParcial;
import clases.NivelEducativo;
import clases.Registro;
import clases.RegistroTardio;
import clases.RegistroTemprano;


public class gestorInscripciones
{
    // Listas de las inscripciones
    private ArrayList<InscripcionParcial> insParcial;
    private ArrayList<InscripcionCompleta> insCompleta;

    // Lista de campamentos
    private ArrayList<Campamento> camps;

    // Lista de asistentes
    private ArrayList<Asistente> asistentes;

    // Clases Factoria
    private Registro reg;
    private RegistroTardio regTard = new RegistroTardio();
    private RegistroTemprano regTemp = new RegistroTemprano();

    // Ficheros Almacenamiento de Datos
    private String inscripcionesFile;
    private String campamentosFile;   
    private String asistentesFile;  
    
    // Constructor

    public gestorInscripciones(String inscripcionesFile_, String campamentosFile_, String asistentesFile_)
    {
        this.inscripcionesFile = inscripcionesFile_;
        this.campamentosFile = campamentosFile_;
        this.asistentesFile = asistentesFile_;
        cargarDatos();
    }

    // Busca el campamento por su id

    private Campamento campamento(int id)
    {
        for (Campamento aux : camps)
        {
            if (id == aux.getId())
            {
                return aux;
            }
        }
        return null;
    }

    // Busca el asistente por su id

    private Asistente asistente(int id)
    {
        for (Asistente aux : asistentes)
        {
            if (id == aux.getId())
            {
                return aux;
            }
        }
        return null;
    }

    // Cuenta el numero de asistentes del campamento

    private int nasistentes(int id)
    {
        int c = 0;
        for (InscripcionCompleta aux : insCompleta)
        {
            if (aux.getIdCamp() == id)
            {
                c++;
            }
        }
        for (InscripcionParcial aux : insParcial)
        {
            if (aux.getIdCamp() == id)
            {
                c++;
            }
        }

        return c;
    }

    // Funciones auxiliares para comprobar que no esten ya inscritos en ese campamento ya sea en la misma modalidad o no

    private boolean inscribirParcial(InscripcionParcial ins)
    {
        for (InscripcionCompleta aux : insCompleta)
        {
            if ((ins.getIdAsis() == aux.getIdAsis()) && (ins.getIdCamp() == aux.getIdCamp()))
            {
                return false;
            }
        }

        for (InscripcionParcial aux : insParcial)
        {
            if ((ins.getIdAsis() == aux.getIdAsis()) && (ins.getIdCamp() == aux.getIdCamp()))
            {
                return true;
            }
        }

        insParcial.add(ins);
        return true;

    }

    private boolean inscribirCompleta(InscripcionCompleta ins)
    {
        for (InscripcionParcial aux : insParcial)
        {
            if ((ins.getIdAsis() == aux.getIdAsis()) && (ins.getIdCamp() == aux.getIdCamp()))
            {
                return false;
            }
        }

        for (InscripcionCompleta aux : insCompleta)
        {
            if ((ins.getIdAsis() == aux.getIdAsis()) && (ins.getIdCamp() == aux.getIdCamp()))
            {
                return true;
            }
        }

        insCompleta.add(ins);
        return true;

    }

    // Añadir Inscripciones

    public boolean inscribirParcial(int id_as, int id_camp, LocalDate fecha)
    {
        Campamento camp = campamento(id_camp);
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            reg = regTemp;
        }
        else if (dias>2)
        {
            reg = regTard;
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            return false;
        }

        InscripcionParcial ins = reg.createRegistroP();
        ins.setIdAsis(id_as);
        ins.setIdCmp(id_camp);
        ins.setFecha(fecha);
        Asistente asis = asistente(id_as);
        ins.setNecesidadEspecial(asis.getAtencionEsp());
        ins.setPrecio(100 + camp.getActividades().size()*20 );
        return inscribirParcial(ins);
    }

    public boolean inscribirCompleta(int id_as, int id_camp, LocalDate fecha)
    {
        Campamento camp = campamento(id_camp);
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            reg = regTemp;
        }
        else if (dias>2)
        {
            reg = regTard;
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            return false;
        }


        InscripcionCompleta ins = reg.createRegistroC();
        ins.setIdAsis(id_as);
        ins.setIdCmp(id_camp);
        ins.setFecha(fecha);
        ins.setPrecio(300 + camp.getActividades().size()*20 );
        Asistente asis = asistente(id_as);
        ins.setNecesidadEspecial(asis.getAtencionEsp());
        return inscribirCompleta(ins);
    }

    // Campamentos disponibles

    public ArrayList<Campamento> campamentos(LocalDate fecha)
    {
        ArrayList<Campamento> camps_ = new ArrayList<Campamento>();
        for (Campamento aux : camps)
        {
            if ( ((int)(aux.getInicio().getDayOfMonth() - fecha.getDayOfMonth()) > 2) && (aux.getMax() > nasistentes(aux.getId()) ) )
            {
                camps_.add(aux);
            }   
        }
        return camps_;
    }

    // Carga la informacion de los ficheros
    
    private void cargarDatos(){

        try{

            FileReader fr = new FileReader(new File(inscripcionesFile));
            BufferedReader lector = new BufferedReader(fr);
            String linea;

            linea = lector.readLine();
            if (linea != "InscripcionesParciales")
            {
                System.out.println("Formato del archivo \"" + inscripcionesFile + "\" erroneo \n");
            }

            // Carga el vector insParcial
            while ((linea = lector.readLine()) != null && linea != "InscripcionesCompletas") 
            {
                String[] partes = linea.split(";");
                if (partes.length == 6) 
                {
                    int id_as = Integer.parseInt(partes[0]);
                    int id_cmp = Integer.parseInt(partes[1]);
                    LocalDate fecha = LocalDate.parse(partes[2]);
                    float precio = Float.parseFloat(partes[3]);

                    boolean asEsp;
                    if(partes[4]=="true")
                    {
                        asEsp = true;
                    }
                    else
                    {
                        asEsp = false;
                    }

                    if(partes[5]=="true")
                    {
                        reg = regTemp;
                    }
                    else
                    {
                        reg = regTemp;
                    }

                    InscripcionParcial inscripcion =  reg.createRegistroP();
                    inscripcion.setIdAsis(id_as);
                    inscripcion.setIdCmp(id_cmp);
                    inscripcion.setFecha(fecha);
                    inscripcion.setPrecio(precio);
                    inscripcion.setNecesidadEspecial(asEsp);
                    insParcial.add(inscripcion);
                }
            }

            // Carga el vector insCompleta
            while ((linea = lector.readLine()) != null) 
            {
                String[] partes = linea.split(";");
                if (partes.length == 6) 
                {
                    int id_as = Integer.parseInt(partes[0]);
                    int id_cmp = Integer.parseInt(partes[1]);
                    LocalDate fecha = LocalDate.parse(partes[2]);
                    float precio = Float.parseFloat(partes[3]);

                    boolean asEsp;
                    if(partes[4]=="true")
                    {
                        asEsp = true;
                    }
                    else
                    {
                        asEsp = false;
                    }

                    if (partes[5]=="true")
                    {
                        reg = regTemp;
                    }
                    else
                    {
                        reg = regTemp;
                    }

                    InscripcionCompleta inscripcion =  reg.createRegistroC();
                    inscripcion.setIdAsis(id_as);
                    inscripcion.setIdCmp(id_cmp);
                    inscripcion.setFecha(fecha);
                    inscripcion.setPrecio(precio);
                    inscripcion.setNecesidadEspecial(asEsp);
                    insCompleta.add(inscripcion);
                }
            }
            fr.close();
            lector.close();

            // Carga el vector camps
            fr = new FileReader(new File(campamentosFile));
            lector = new BufferedReader(fr);

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    int id = Integer.parseInt(partes[0]);
                    LocalDate inicio = LocalDate.parse(partes[1]);
                    LocalDate fin = LocalDate.parse(partes[2]);
                    NivelEducativo nivel;
                    if (partes[3] == "Infantil")
                    {
                        nivel = NivelEducativo.Infantil;
                    }
                    else if (partes[3] == "Juvenil")
                    {
                        nivel = NivelEducativo.Juvenil;
                    }
                    else
                    {
                        nivel = NivelEducativo.Adolescente;
                    }
                    int nMax = Integer.parseInt(partes[4]);
                    
                    camps.add(new Campamento(id, inicio, fin, nivel, nMax));
                }
            }
            fr.close();
            lector.close();

            // Carga el vector asistentes
            fr = new FileReader(new File(asistentesFile));
            lector = new BufferedReader(fr);

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    LocalDate fecha = LocalDate.parse(partes[2]);
                    boolean especial;
                    if(partes[3]=="true"){
                        especial = true;
                    }
                    else{
                        especial = false;
                    }
                    Asistente asistente = new Asistente(id, nombre, fecha, especial);
                    asistentes.add(asistente);
                }
            }
            fr.close();
            lector.close();

        }catch(IOException e){

            e.printStackTrace();
        }
    }

    // Guarda la informacion en los ficheros

    public void guardar(){

        try{
            BufferedWriter escritor = new BufferedWriter(new FileWriter(inscripcionesFile));
            escritor.write("InscripcionesParciales");
            escritor.newLine();
            for (InscripcionParcial inscripcion : insParcial){

                escritor.write(inscripcion.getIdAsis() + ";" + inscripcion.getIdCamp() + ";" + inscripcion.getFecha() + ";" + inscripcion.getPrecio() + ";" + inscripcion.getNecesidadEspecial() + ";" + inscripcion.getBooleanCancel());
                escritor.newLine();
            }

            escritor.write("Inscripcionescompletas");
            escritor.newLine();
            for (InscripcionCompleta inscripcion : insCompleta){

                escritor.write(inscripcion.getIdAsis() + ";" + inscripcion.getIdCamp() + ";" + inscripcion.getFecha() + ";" + inscripcion.getPrecio() + ";" + inscripcion.getNecesidadEspecial() + ";" + inscripcion.getBooleanCancel());
                escritor.newLine();
            }
            escritor.close();
            
        } catch (IOException e){

            e.printStackTrace();
        }
    }

}