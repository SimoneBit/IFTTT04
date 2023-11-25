package Action;

/**
 *L'interfaccia Action rappresenta un'azione che può essere eseguita.
 * Le classi che implementano questa interfaccia devono fornire un'implementazione
 * per il metodo @see #executeAction(), che definisce il comportamento dell'azione.
 * 
 * @author Simone Pacifico
 */
public interface Action {
    
    /**
     * Esegue l'azione specifica implementata da una classe che implementa
     * l'interfaccia Action.
     * @return true se l'azione è stata eseguita con successo, false altrimenti.
     */
    public boolean executeAction();

    
}
