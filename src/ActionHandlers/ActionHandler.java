package ActionHandlers;

import Action.*;

/**
 *L'interfaccia ActionHandler rappresenta un gestore di azioni nel contesto del pattern di Chain of Responsibility. 
 * Un gestore di azioni può essere responsabile di eseguire un'azione o di passare la richiesta al successivo 
 * gestore nella catena.
 * 
 * I gestori di azioni devono implementare il metodo @see #handle per gestire la richiesta in modo specifico. 
 * Il metodo @see #setNext consente di impostare il successivo gestore nella catena.
 * 
 * @see Action
 * @see <https://refactoring.guru/design-patterns/chain-of-responsibility>
 * @author Simone Pacifico
 */
public interface ActionHandler {
    
    /**
     * Imposta il successivo gestore nella catena.
     * @param h il successivo gestore nella catena.
     */
    public void setNext(ActionHandler h);
    
    
    /**
     * Gestisce la richiesta in base alle regole definite dall'implementazione.
     * Se il gestore è in grado di gestire la richiesta, restituisce un'istanza di {@see Action} rappresentante l'azione eseguita. 
     * Altrimenti, passa la richiesta al successivo gestore nella catena.
     * @param request la richiesta da gestire.
     * @param param parametri aggiuntivi necessari per la gestione della richiesta.
     * @return un'istanza di {@see Action} se la richiesta è stata gestita, altrimenti null.
     */
    public abstract Action handle(String request, String param);
}
