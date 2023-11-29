
package ActionHandlers;

import Rule.Rule;
import java.io.*;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author 39349
 */
public class RuleFileHandler extends BaseActionHandler{
    private final String filePath;

    public RuleFileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Metodo per salvare le regole su file
    public void saveRules(List<Rule> rules) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(rules);
            System.out.println("Regole salvate con successo");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio delle regole");
        }
    }
    
    // Metodo per caricare le regole da file
    public List<Rule> loadRules() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            Object object = ois.readObject();
            if (object instanceof List) {
                System.out.println("Regole caricate con successo");
                return (List<Rule>) object;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Primo avvio. Nessun file di salvataggio trovato.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore durante il caricamento delle regole");
        }
        return Collections.emptyList();
    }
}
