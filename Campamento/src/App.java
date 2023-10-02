

import java.sql.Date;

public class App {
    public static void main(String[] args){
        Asistente Andres = new Asistente(21,"Andres Sanchez",Date.valueOf("2023-10-01"),true);
        Monitor Carlos = new Monitor(32, "Carlos Ruiz Martinez", true);
        System.out.println(Andres.toString() + "\n" + Carlos.toString());
    }
}
