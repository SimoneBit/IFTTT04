package Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe WriteOnFileAction implementa l'interfaccia @see Action e fornisce un'azione per scrivere una stringa 
 * su un file. L'azione può essere eseguita chiamando il metodo @see #executeAction().
 * @author Palma Orlando
 */
public class WriteOnFileAction implements Action {
    
    private File file;
    private String stringToAppend;
    
    /**
     * Costruisce un nuovo oggetto WriteOnFileAction con il file su cui scrivere e la stringa da aggiungere.
     * @param f il file su cui scrivere la stringa.
     * @param stringToAppend la stringa da aggiungere al file.
     */
    public WriteOnFileAction(File f, String stringToAppend){
        this.file = f;
        this.stringToAppend = stringToAppend;
        
    }

    /**
     * Esegue l'azione di scrittura di una stringa nel file associato a questa istanza.
     * La stringa viene scritta nel file seguita da un carattere di nuova linea. Se il file non esiste, viene creato. 
     * Se il file esiste già, la stringa viene aggiunta alla fine del file senza sovrascrivere il contenuto esistente.
     * @return true se l'operazione di scrittura ha avuto successo, false altrimenti.   
     */
    @Override
    public boolean executeAction() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(stringToAppend);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
