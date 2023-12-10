package Condition;

import java.io.IOException;
import java.io.Serializable;

/**
 * Questa classe implementa l'interfaccia @see Condition e rappresenta una condizione basata sullo stato di 
 * uscita di un programma esterno eseguito tramite Java. La classe è anche serializzabile.
 * @author Palma Orlando
 */
public class ExitStatusCondition implements Condition, Serializable{
    private String program;
    private int expectedValue;
    private boolean checkedToday;
    private boolean not;
    
    /**
     * Costruttore della classe. 
     * Inizializza l'istanza con il percorso del programma, il valore di uscita atteso e il flag di negazione specificati.
     * 
     * @param program il percorso del programma da eseguire.
     * @param expectedValue il valore di uscita atteso dal programma.
     * @param not flag che indica se la condizione deve essere negata.
     */
    public ExitStatusCondition(String program, int expectedValue, boolean not) {
        this.program = program;
        this.expectedValue = expectedValue;
        this.not = not;
    }
    
    /**
     * Verifica se la condizione è attualmente soddisfatta in base allo stato di uscita del programma esterno.
     * 
     * @return true se la condizione è soddisfatta,  false altrimenti.
     */
    @Override
    public boolean checkCondition() {
        boolean cond = false;
        try {
            // Costruisco il comando per eseguire il file specificato nella bash shell
            //ProcessBuilder processBuilder = new ProcessBuilder("C:\\\\Cygwin64\\\\bin\\\\bash.exe", "-c", "java -jar \"" + program.replace("\\", "/") + "\"");
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "java -jar \"" + program.replace("\\", "/") + "\"");
            // Eseguo il processo esterno
            Process processo = processBuilder.start();
            // Confronta il valore di uscita con il valore atteso
            int exitValue = processo.waitFor();
            cond = exitValue == expectedValue;
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return cond ^ not;
    }

    /**
     * Resetta lo stato della condizione, segnando che è stata verificata oggi.
     */
    @Override
    public void resetState() {
        return;
    }
       
    
}
