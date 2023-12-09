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
    private boolean not;

    public ExitStatusCondition(String program, int expectedValue, boolean not) {
        this.program = program;
        this.expectedValue = expectedValue;
        this.not = not;
    }
    
    
    @Override
    public boolean checkCondition() {
        try {
            // Costruisco il comando per eseguire il file specificato nella bash shell
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\\\Cygwin64\\\\bin\\\\bash.exe", "-c", "java -jar \"" + program.replace("\\", "/") + "\"");
            // Eseguo il processo esterno
            Process processo = processBuilder.start();

            // Ottiengo il valore di uscita del processo
            int exitValue = processo.waitFor();
            //System.out.println("Valore uscita: " + exitValue);
            boolean cond = exitValue == expectedValue;

            // Confronta il valore di uscita con il valore atteso
            if (cond && !checkedToday) {
                return !not;  // Se cond è vera e checkedToday è falso, restituisci il valore di !not
            } else if (!cond && not) {
            checkedToday = true;
            return true;  // Se cond è falsa e not è true, setta checkedToday a true e restituisci true
            } 
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        checkedToday = !not; // Altrimenti, setta checkedToday a !not
        return not;  // Restituisci il valore di not
    }

    @Override
    public void resetState() {
        return;
    }
       
    
}
