
package ActionHandlers;

import Rule.Rule;
import java.io.*;
import java.util.Collections;
import java.util.List;


/**
 * Questa classe estende @see BaseActionHandler e fornisce funzionalità per salvare e caricare
 * le regole su e da un file specificato. La classe è anche serializzabile, rendendola adatta per l'inclusione in 
 * flussi di dati e la persistenza.
 * 
 * @author Nicola Lanzara
 */
public class RuleFileHandler extends BaseActionHandler implements Serializable{
    private final String filePath;

    public RuleFileHandler(String filePath) {
        this.filePath = filePath;
    }

    
    /**
     * Salva una lista di regole su file.
     * @param rules la lista di regole da salvare.
     */
    public void saveRules(List<Rule> rules) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(rules);
            System.out.println("Regole salvate con successo");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio delle regole");
        }
    }
    
    /**
     * Carica le regole da un file.
     * @return la lista di regole caricate dal file o una lista vuota se non è possibile caricare le regole.
     */
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
