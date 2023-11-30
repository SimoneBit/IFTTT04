package Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Palma Orlando
 */
public class WriteOnFileAction implements Action {
    
    private File file;
    private String stringToAppend;
    
    public WriteOnFileAction(File f, String stringToAppend){
        this.file = f;
        this.stringToAppend = stringToAppend;
        
    }

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
    
    public File getFile() {
        return file;
    }

    
}
