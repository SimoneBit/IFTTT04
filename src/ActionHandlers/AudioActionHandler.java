/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActionHandlers;

import Action.*;
import java.io.File;

/**
 *
 * @author Simone
 */
public class AudioActionHandler extends BaseActionHandler {

    @Override
    public Action handle(String request, String param) {
        if(request.equals("Riproduci il file")){
            File f = new File(param);
            PlayFileAction pfa = new PlayFileAction(f);
            return pfa;

        }else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        return null;
    }
    
    
}
