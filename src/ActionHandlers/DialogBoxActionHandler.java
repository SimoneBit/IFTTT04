/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActionHandlers;

import Action.*;

/**
 *
 * @author Simone
 */
public class DialogBoxActionHandler extends BaseActionHandler {

    @Override
    public Action handle(String request, String param) {
        if(request.compareTo("Mostra il messaggio") == 0 ){
           DialogBoxAction dba = new DialogBoxAction(param); 
           return dba;
        }
        else{
            if(next != null){
                return next.handle(request, param);
            }
        }
        
        return null;
    }
    
}
