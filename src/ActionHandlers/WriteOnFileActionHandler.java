package ActionHandlers;

import Action.*;
import java.io.File;

/**
 * La classe WriteOnFileActionHandler estende @see BaseActionHandler e gestisce le richieste relative 
 * all'azione di scrittura su file. Accetta richieste con il formato "Scrivi sul file" e un parametro che include il percorso del file 
 * su cui scrivere e il testo da scrivere separati dalla stringa " Testo da scrivere: ".
 * 
 * La classe analizza la richiesta e il parametro, crea un'istanza di @see WriteOnFileAction con i dati forniti e restituisce 
 * l'istanza pronta per l'esecuzione. Se la richiesta non corrisponde a "Scrivi sul file", delega la gestione alla classe successiva 
 * nella catena attraverso il metodo handle della classe padre BaseActionHandler.
 * 
 * @author Palma Orlando
 */
public class WriteOnFileActionHandler extends BaseActionHandler {
    
    /**
     * Gestisce la richiesta di scrittura su file e restituisce un'istanza di WriteOnFileAction pronta per l'esecuzione.
     *
     * @param request la richiesta da gestire.
     * @param param un array di stringhe che rappresenta il parametro associato alla richiesta. Esso include il percorso del 
     *                              file su cui scrivere e il testo da scrivere separati dalla stringa " Testo da scrivere: ".
     * @return un'istanza di WriteOnFileAction se la richiesta è "Scrivi sul file", altrimenti passa la richiesta alla classe 
     *                 successiva nella catena e restituisce null.
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Scrivi sul file")){
            String[] part = param.split(" Testo da scrivere: ");
            String paramFile = part[0];
            String paramString = part[1];
            File fileToWrite = new File(paramFile);
            WriteOnFileAction stf = new WriteOnFileAction(fileToWrite, paramString);
            return stf;          
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
    
}
