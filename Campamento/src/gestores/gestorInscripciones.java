package gestores;

import java.util.ArrayList;
import java.sql.Date;

public class gestorInscripciones
{
    // Listas de las inscripciones
    private ArrayList<InscripcionParcial> insParcial;
    private ArrayList<InscripcionCompleta> insCompleta;

    // Lista de campamentos
    private ArrayList<Campamento> camps;

    // Clases Factoria
    private Registro reg;
    private RegistroTardio regTard = new RegistroTardio();
    private RegistroTemprano regTemp = new RegistroTemprano();

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

    // Añadir Inscripciones
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

    public boolean inscribirParcial(int id_as, int id_camp, Date fecha)
    {
        Campamento camp = campamento(id_camp);
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getTime() - fecha.getTime());
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

        InscripcionParcial ins = reg.creaateInscripcionParcial();
        ins.setIdAsis(id_as);
        ins.setIdCmp(id_camp);
        ins.setFecha(fecha);
        ins.setPrecio(100.0 + camp.getActividades().size()*20.0 );
        return inscribirParcial(ins);
    }

    public boolean inscribirCompleta(int id_as, int id_camp, Date fecha)
    {
        Campamento camp = campamento(id_camp);
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getTime() - fecha.getTime());
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


        InscripcionCompleta ins = reg.createInscripcionC();
        ins.setIdAsis(id_as);
        ins.setIdCmp(id_camp);
        ins.setFecha(fecha);
        ins.setPrecio(300.0 + camp.getActividades().size()*20.0 );
        return inscribirCompeta(ins);
    }

    // Campamentos disponibles

    public ArrayList<Campamento> campamentos(Date fecha)
    {
        ArrayList<Campamento> camps_;
        for (Campamento aux : camps)
        {
            if ( ((int)(aux.getInicio().getTime() - fecha.getTime()) > 2) && (aux.getMax() > nasistentes(aux.getId()) ) )
            {
                camps_.add(aux);
            }   
        }
        return camps_;
    }
    

}