package ActionHandlers;

import Action.*;
import java.io.File;

/**
 * La classe MoveFileActionHandler estende @see BaseActionHandler e gestisce richieste relative all'azione di 
 * spostamento di file. Accetta richieste con il formato "Sposta il file" e un parametro che include il percorso del file da 
 * spostare e la directory di destinazione separati dalla stringa " Path di destinazione: ".
 * 
 * La classe analizza la richiesta e il parametro, crea un'istanza di @see MoveFileAction con i dati forniti e restituisce l'istanza 
 * pronta per l'esecuzione. Se la richiesta non corrisponde a "Sposta il file", delega la gestione alla classe successiva nella 
 * catena attraverso il metodo handle della classe padre BaseActionHandler.
 * 
 * @author Palma Orlando
 */
public class MoveFileActionHandler extends BaseActionHandler{

    /**
     * Gestisce la richiesta di spostamento del file e restituisce un'istanza di MoveFileAction pronta per l'esecuzione.
     *
     * @param request la richiesta da gestire.
     * @param param un array di stringhe che rappresenta il parametro associato alla richiesta. Esso include il percorso del
     *                              file da spostare e la directory di destinazione separati dalla stringa " Path di destinazione: ".
     * @return un'istanza di MoveFileAction se la richiesta è "Sposta il file", altrimenti passa la richiesta alla classe successiva 
     *                 nella catena e restituisce null.
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Sposta il file")){
            String[] part = param.split(" Path di destinazione: ");
            String paramFile = part[0];
            String paramPath = part[1];
            File fileToMove = new File(paramFile);
            MoveFileAction cf = new MoveFileAction(fileToMove, paramPath);
            return cf;          
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
    
}
