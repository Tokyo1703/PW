package gestores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import clases.Asistente;

public class gestorAsistentes{

    private String DataArchive;
    private List<Asistente> lista;
    
    public gestorAsistentes(String Data){

        DataArchive = Data;
    }

    private void volcarDatos(){

        try{

            FileReader archivo = new FileReader(DataArchive);
            BufferedReader lector = new BufferedReader(archivo);

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    int id = partes[0];
                    String nombre = partes[1];
                    Date fecha = partes[2];
                    boolean especial = partes[3];
                    Asistente asistente = new Participantes(id, nombre, fecha, especial);
                    lista.add(asistente);
                }
            }
            lector.close();

        }catch(IOException e){

                e.printStackTrace();
            }
    }

    private void guardar(){

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(DataArchive))){

            for (Asistente asist : lista){

                escritor.write(asist.getId() + ";" + asist.getNombreCompleto() + ";" + asist.getFenachaNacimniento() + ";" + asist.getAtencionEsp() );
                escritor.newLine();
            }
        } catch (IOException e){

            e.printStackTrace();
        }
    }

    public boolean addAsist(Asistente Nuevo){

        volcarDatos(); 

        for(Asistente asist : lista){

            if(asist == Nuevo){

                System.out.println("El asistente ya esta registrado\n");

                return true;
            }
        }

        if(lista.add(Nuevo)){

            guardar();
            return true;
        }

        return false;
    }

    public boolean editAsist(int id, Asistente Editado){

        int i = 0;

        for(Asistente asist : lista){

            if(asist.getId() == id){

                lista.set(i, Editado);
                guardar();
                return true;
            }

            i++;
        }

        System.out.println("No se encontro ese participante\n");
        return false;
    }

    public void print(){

        for(Asistente asist : lista){

            System.out.println(asist);
        }
    }
}