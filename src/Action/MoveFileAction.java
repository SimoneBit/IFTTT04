package Action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;

/**
 *
 * @author Palma Orlando
 */
public class MoveFileAction implements Action, Serializable{
    
    private File file;
    private String destinationPath;

    public MoveFileAction(File file, String destinationPath) {
        this.file = file;
        this.destinationPath = destinationPath;
    }
    
    

    @Override
    public boolean executeAction() {
        Path targetDirectory = Paths.get(destinationPath);
        try {
            // Crea il percorso completo del file di destinazione nella directory di destinazione e copia il file nella directory 
            //di destinazione con l'opzione di sovrascrittura se esiste già
            Files.move(file.toPath(), targetDirectory.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
