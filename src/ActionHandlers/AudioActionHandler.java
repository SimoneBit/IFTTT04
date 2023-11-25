package ActionHandlers;

import Action.*;
import java.io.File;

/**
 * La classe AudioActionHandler estende la classe @see BaseActionHandler e implementa la gestione delle 
 * richieste audio all'interno di una catena di responsabilità.
 * Questo gestore gestisce richieste specifiche legate alla riproduzione di file audio. Se la richiesta è "Riproduci il file", 
 * crea un'istanza di @see PlayFileAction e la restituisce. Altrimenti, passa la richiesta al successivo gestore nella catena.
 * @author Simone
 */
public class AudioActionHandler extends BaseActionHandler {

    
    /**
     * Gestisce la richiesta audio in base alle regole definite dall'implementazione. Se la richiesta è "Riproduci il file", 
     * crea un'istanza di @see PlayFileAction con il file specificato e la restituisce. Altrimenti passa la richiesta al successivo
     * gestore nella catena se presente.
     *
     * @param request la richiesta da gestire.
     * @param param parametri aggiuntivi necessari per la gestione della richiesta.
     * @return un'istanza di @see PlayFileAction se la richiesta è stata gestita, altrimenti il risultato della gestione
     *                  da parte del successivo gestore nella catena, o null se nessun gestore può gestire la richiesta.
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Riproduci il file")){
            File f = new File(param);
            PlayFileAction pfa = new PlayFileAction(f);
            return pfa;

        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
    
    
}
