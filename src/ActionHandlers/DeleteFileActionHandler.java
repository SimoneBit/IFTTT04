package ActionHandlers;

import Action.Action;
import Action.DeleteFileAction;
import java.io.File;

/**
 * Questa classe estende @see BaseActionHandler e fornisce la gestione delle richieste per l'azione di eliminare 
 * un file specificato.
 * @author Simone Pacifico
 */
public class DeleteFileActionHandler extends BaseActionHandler{
    
    /**
     * Gestisce la richiesta di eliminare un file
     * @param request la richiesta di azione.
     * @param param il parametro associato alla richiesta. Rappresenta il percorso del file da eliminare.
     * @return un'istanza di @see DeleteFileAction se la richiesta è "Elimina il file", altrimenti delega la richiesta al gestore 
     *                 successivo nella catena, se presente. Restituisce null se la richiesta non può essere gestita. 
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Elimina il file")){
            File f = new File(param);
            DeleteFileAction pfa = new DeleteFileAction(f);
            return pfa;

        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
}
