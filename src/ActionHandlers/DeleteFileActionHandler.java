/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActionHandlers;

import Action.Action;
import Action.DeleteFileAction;
import java.io.File;

/**
 *
 * @author Simone
 */
public class DeleteFileActionHandler extends BaseActionHandler{
    
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
