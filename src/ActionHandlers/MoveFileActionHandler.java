package ActionHandlers;

import Action.*;
import java.io.File;

/**
 *
 * @author Palma Orlando
 */
public class MoveFileActionHandler extends BaseActionHandler{

    @Override
    public Action handle(String request, String param) {
        if(request.equals("Sposta il file")){
            String[] part = param.split(" Path di destinazione: ");
            String paramFile = part[0];
            String paramPath = part[1];
            System.out.println("Param 0: " +paramFile);
            System.out.println("Param 1: " +paramPath);
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
