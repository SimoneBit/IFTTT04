package ActionHandlers;

import Action.*;

/**
 * La classe DialogBoxActionHandler estende la classe @see BaseActionHandler e implementa la gestione 
 * delle richieste legate alla visualizzazione di finestre di dialogo. Questo gestore può gestire richieste specifiche 
 * come "Mostra il messaggio", creando un'istanza di @see DialogBoxAction con il messaggio specificato e restituendola.
 * Se la richiesta non è gestita, passa la richiesta al successivo gestore nella catena.
 * 
 * @author Miriam Vitolo
 */
public class DialogBoxActionHandler extends BaseActionHandler {

    
    /**
     * Gestisce la richiesta di visualizzazione della finestra di dialogo in base alle regole definite 
     * dall'implementazione. Se la richiesta è "Mostra il messaggio", crea un'istanza di @see DialogBoxAction con il 
     * messaggio specificato e la restituisce. Altrimenti passa la richiesta al successivo gestore nella catena, se presente.
     * 
     * @param request la richiesta da gestire.
     * @param param parametri aggiuntivi necessari per la gestione della richiesta.
     * @return un'istanza di DialogBoxAction se la richiesta è stata gestita, altrimenti il risultato della gestione da parte 
     *                  del successivo gestore nella catena, o null se nessun gestore può gestire la richiesta.
     */
    @Override
    public Action handle(String request, String param) {
        if(request.compareTo("Mostra il messaggio") == 0 ){
           DialogBoxAction dba = new DialogBoxAction(param); 
           return dba;
        }
        else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        
        return null;
    }
    
}
