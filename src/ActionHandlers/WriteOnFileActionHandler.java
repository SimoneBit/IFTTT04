package ActionHandlers;

import Action.*;
import java.io.File;

/**
 *
 * @author Palma Orlando
 */
public class WriteOnFileActionHandler extends BaseActionHandler {
    
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
