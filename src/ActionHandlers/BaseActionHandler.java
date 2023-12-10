package ActionHandlers;

import Action.Action;

/**
 *La classe BaseActionHandler implementa l'interfaccia ActionHandler e fornisce un'implementazione di base 
 * per la gestione delle richieste in una catena di responsabilità. Ogni gestore di azioni nella catena è collegato al 
 * successivo attraverso l'attributo next.
 * 
 * Questa classe fornisce metodi per impostare il successivo gestore nella catena e per
 * gestire una richiesta passandola al successivo gestore, se presente.
 * @author Miriam Vitolo
 */
public class BaseActionHandler implements ActionHandler{
    ActionHandler next;
    
    
    /**
     * Imposta il successivo gestore nella catena di responsabilità.
     * @param h Il successivo gestore nella catena.
     */
    @Override
    public void setNext(ActionHandler h) {
        this.next = h;
    }

    
    
    /**
     * Gestisce la richiesta passandola al successivo gestore nella catena, se presente.
     * @param request la richiesta da gestire.
     * @param param parametri aggiuntivi necessari per la gestione della richiesta.
     * @return il risultato della gestione da parte del successivo gestore nella catena.
     */
    @Override
    public Action handle(String request, String param) {
        return next.handle(request, param);
    }

    
    
    /**
     * Restituisce il successivo gestore nella catena.
     * @return il successivo gestore nella catena.
     */
    public ActionHandler getNext() {
        return next;
    }
    
    
}
