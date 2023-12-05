package Action;

import java.io.File;
import java.io.Serializable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe ExecuteProgramAction implementa l'interfaccia Action e fornisce un'azione per eseguire un programma
 * con i parametri specificati. L'azione può essere eseguita chiamando il metodo executeAction().
 * @author Nicola Lanzara
 */
public class ExecuteProgramAction implements Action, Serializable {

    private String programPath;
    private String programParameters;

    /**
     * Costruisce un nuovo oggetto ExecuteProgramAction con il percorso del programma e i parametri da passare.
     * @param programPath il percorso del programma da eseguire.
     * @param programParameters i parametri da passare al programma.
     */
    public ExecuteProgramAction(String programPath, String programParameters) {
        this.programPath = programPath;
        this.programParameters = programParameters;
    }

    /**
     * Esegue l'azione di esecuzione del programma con i parametri specificati.
     * @return true se l'operazione di esecuzione ha avuto successo, false altrimenti.   
     */
    @Override
    public boolean executeAction() {
        try {
            // Esegui il programma con i parametri specificati
            Runtime.getRuntime().exec(programPath + " " + programParameters);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
