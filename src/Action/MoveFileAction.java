package Action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;

/**
 *La classe MoveFileAction implementa l'interfaccia @see Action e fornisce un'azione per spostare un file in una 
 * directory specificata. L'azione può essere eseguita chiamando il metodo @see #executeAction().
 * @author Palma Orlando
 */
public class MoveFileAction implements Action, Serializable{
    
    private File file;
    private String destinationPath;
    
    /**
     * Costruisce un nuovo oggetto MoveFileAction con il file da spostare e il percorso di destinazione specificato.
     * @param file il file da spostare.
     * @param destinationPath il percorso di destinazione in cui spostare il file.
     */

    public MoveFileAction(File file, String destinationPath) {
        this.file = file;
        this.destinationPath = destinationPath;
    }
    
    /**
     * Esegue l'azione di spostamento del file associato a questa istanza nella directory di destinazione specificata.
     * Se il file di destinazione esiste già, viene sovrascritto con il file sorgente.
     * @return true se l'operazione di spostamento è riuscita, altrimenti false.
     */

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
