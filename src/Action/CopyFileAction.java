package Action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.*;

/**
 *La classe CopyFileAction implementa l'interfaccia @see Action e fornisce un'azione per copiare un file in una 
 * directory specificata. L'azione può essere eseguita chiamando il metodo @see #executeAction().
 * @author Palma Orlando
 */
public class CopyFileAction implements Action, Serializable {
    private File file;
    private String destinationPath;
    
    /**
     * Costruisce un nuovo oggetto CopyFileAction con il file da copiare e il percorso di destinazione specificato.
     *
     * @param file il file da copiare.
     * @param destinationPath il percorso di destinazione in cui copiare il file.
     */

    public CopyFileAction(File file, String destinationPath) {
        this.file = file;
        this.destinationPath = destinationPath;
    }
        
/**
 * Esegue l'azione di copia del file associato a questa istanza nella directory di destinazione specificata.
 * Se il file di destinazione esiste già, viene sovrascritto con il contenuto del file sorgente.
 * @return true se l'operazione di copia è riuscita, altrimenti false.
 */
    @Override
    public boolean executeAction() {
        Path targetDirectory = Paths.get(destinationPath);
        try {
            // Crea il percorso completo del file di destinazione nella directory di destinazione e copia il file nella directory 
            //di destinazione con l'opzione di sovrascrittura se esiste già
            Files.copy(file.toPath(), targetDirectory.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
