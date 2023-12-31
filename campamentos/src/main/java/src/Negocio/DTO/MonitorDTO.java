package src.Negocio.DTO;

public class MonitorDTO {
    private int id;
    private String NombreCompleto;
    private boolean AtencionEsp;

    public MonitorDTO(){

    }

    public MonitorDTO(int id, String NombreCompleto, boolean AtencionEsp){
        this.id = id;
        this.NombreCompleto = NombreCompleto;
        this.AtencionEsp = AtencionEsp;
    }

    public int getId(){
        return id;
    }

    public String getNombreCompleto(){
        return NombreCompleto;
    }

    public boolean getAtencionEsp(){
        return AtencionEsp;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNombreCompleto(String Nombre){
        NombreCompleto = Nombre;
    }

    public void setAtencionEsp(boolean AtencionEsp){
        this.AtencionEsp = AtencionEsp;
    }

    public String toString(){
        String atencion;

        if(AtencionEsp==false){
            atencion="No";
        }
        else{
            atencion="Si";
        }

        String infoMonitor = "Identificador: " + id + "\n" +
                        "Nombre y apellidos: " + NombreCompleto + "\n" + 
                        "Atencion especial: " + atencion + "\n";

        
        return infoMonitor;               
    }    
}
