package Condition;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Palma Orlando
 */
public class ExitStatusCondition implements Condition, Serializable{
    private String program;
    private int expectedValue;
    private boolean checkedToday;

    public ExitStatusCondition(String program, int expectedValue) {
        this.program = program;
        this.expectedValue = expectedValue;
    }
    
    
    @Override
    public boolean checkCondition() {
        try {
            //System.out.println("Path Jar: "+program);
            //System.out.println("Valore atteso: "+expectedValue);
            // Costruisco il comando per eseguire il file specificato nella bash shell
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\\\Cygwin64\\\\bin\\\\bash.exe", "-c", "java -jar \"" + program.replace("\\", "/") + "\"");
            //System.out.println("Comando bash andato");

            // Eseguo il processo esterno
            Process processo = processBuilder.start();

            // Ottiengo il valore di uscita del processo
            int exitValue = processo.waitFor();
            //System.out.println("Valore uscita: " + exitValue);

            // Confronta il valore di uscita con il valore atteso
            if (exitValue == expectedValue) {
                //System.out.println("Il valore di uscita è conforme alle aspettative.");
                return true;
            } else {
                //System.out.println("Il valore di uscita non è conforme alle aspettative.");
                return false;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetState() {
        return;
    }
       
    
}
