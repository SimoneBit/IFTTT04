
package Action;

import java.io.File;

/**
 *
 * @author Simone Pacifico
 */
public class DeleteFileAction implements Action {
    
    File file;

    public DeleteFileAction(File f) {
        this.file = f;
    }

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
