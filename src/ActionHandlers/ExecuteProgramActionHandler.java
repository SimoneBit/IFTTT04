package ActionHandlers;

import Action.Action;
import Action.ExecuteProgramAction;

/**
 * La classe ExecuteProgramActionHandler estende la classe BaseActionHandler e gestisce le richieste relative 
 * all'azione di esecuzione di un programma. Accetta richieste con il formato "Esegui il programma" e un parametro
 * che include il percorso del programma e i parametri da passare.
 * 
 * La classe analizza la richiesta e il parametro, crea un'istanza di ExecuteProgramAction con i dati forniti e restituisce 
 * l'istanza pronta per l'esecuzione. Se la richiesta non corrisponde a "Esegui il programma", delega la gestione 
 * alla classe successiva nella catena attraverso il metodo handle della classe padre BaseActionHandler.
 * 
 * @author Nicola Lanzara
 */
public class ExecuteProgramActionHandler extends BaseActionHandler {
    
    /**
     * Gestisce la richiesta di esecuzione del programma e restituisce un'istanza di ExecuteProgramAction pronta per l'esecuzione.
     *
     * @param request la richiesta da gestire.
     * @param param il parametro associato alla richiesta, che include il percorso del programma e i parametri da passare.
     * @return un'istanza di ExecuteProgramAction se la richiesta è "Esegui il programma", altrimenti passa la richiesta 
     *                 alla classe successiva nella catena e restituisce null.
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Esegui il programma")){
            // Analizza il parametro per ottenere il percorso del programma e i parametri
            String[] parts = param.split(" con parametri: ");
            String programPath = parts[0];
            String programParameters = parts[1];
            
            // Crea un'istanza di ExecuteProgramAction con i dati forniti
            ExecuteProgramAction executeProgramAction = new ExecuteProgramAction(programPath, programParameters);
            return executeProgramAction;          
        } else {
            // Delega la gestione alla classe successiva nella catena
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
}
