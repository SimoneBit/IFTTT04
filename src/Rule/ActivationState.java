package Rule;

/**
 *L'interfaccia ActivationState rappresenta lo stato di una regola che può essere attiva o meno.
 * Le due classi State che implementano l'interfaccia devono fornire i metodi @see #executeRule(), @see #checkRule()
 * e @see #isActive() che avranno comportamenti diversi in base allo stato.
 * @author Simone Pacifico
 */
public interface ActivationState{
    
    public boolean executeRule(Rule rule);
    public boolean checkRule(Rule rule);
    public boolean isActive();
}
