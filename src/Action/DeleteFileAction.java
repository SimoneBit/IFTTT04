package Action;

import java.io.File;
import java.io.Serializable;

/**
 * Questa classe implementa l'interfaccia @see Action e rappresenta un'azione che ha lo scopo di eliminare un file 
 * specificato. La classe è anche serializzabile.
 * @author Simone Pacifico
 */
public class DeleteFileAction implements Action, Serializable{
    
    File file;

    public DeleteFileAction(File f) {
        this.file = f;
    }

    /**
     * Esegue l'azione di eliminare il file specificato.
     * 
     * @return true se l'azione ha avuto successo e il file è stato eliminato, false altrimenti 
     */
    @Override
    public boolean executeAction() {
        boolean exit;
        //Controlla se il file esiste
        if (file.exists()) {
            // Cerca di eliminare il file
            if (file.delete()) {
                exit = true;
            } else {
                exit = false;
            }
        } else {
            exit = false;
        }
        return exit;
    }
    
    public File getFile() {
        return file;
    }
}
