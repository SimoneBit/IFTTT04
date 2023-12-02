package ActionHandlers;

import Action.*;
import java.io.File;

/**
 * La classe CopyFileActionHandler estende @see BaseActionHandler e gestisce richieste relative all'azione di copia di file.
 * Accetta richieste con il formato "Copia il file" e un parametro che include il percorso del file da copiare e la directory di 
 * destinazione separati dalla stringa " Path di destinazione: ".
 * 
 * La classe analizza la richiesta e il parametro, crea un'istanza di @see CopyFileAction con i dati forniti e restituisce l'istanza 
 * pronta per l'esecuzione.Se la richiesta non corrisponde a "Copia il file", delega la gestione alla classe successiva nella catena
 * attraverso il metodo @see #handle della classe padre BaseActionHandler.
 * 
 * @author Palma Orlando
 */
public class CopyFileActionHandler extends BaseActionHandler{

    /**
     *Gestisce la richiesta di copia del file e restituisce un'istanza di @see CopyFileAction pronta per l'esecuzione.
     *
     * @param request la richiesta da gestire.
     * @param param un array di stringhe che rappresenta il parametro associato alla richiesta. Esso include il percorso 
     *                              del file da copiare e la directory di destinazione separati dalla stringa " Path di destinazione: ".
     * @return un'istanza di CopyFileAction se la richiesta è "Copia il file", altrimenti passa la richiesta alla classe successiva 
     *                 nella catena e restituisce null. 
     */
    @Override
    public Action handle(String request, String param) {
        if(request.equals("Copia il file")){
            String[] part = param.split(" Path di destinazione: ");
            String paramFile = part[0];
            String paramPath = part[1];
            System.out.println("Param 0: " +paramFile);
            System.out.println("Param 1: " +paramPath);
            File fileToCopy = new File(paramFile);
            CopyFileAction cf = new CopyFileAction(fileToCopy, paramPath);
            return cf;          
        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }

}
