/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import Action.Action;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author miria
 */

public class checkRules extends Thread {
    
    private RulesSet r;
    
    public checkRules(RulesSet rs){
        this.r= rs;
    }
    
    
    @Override
    public void run() {
        // Ciclo infinito, il thread continua ad eseguirsi indefinitamente
        while (true) { 
            for (Rule rule: r.getRuleList()){
                if(rule.isActive()){
                    if (rule.getTrigger().checkTrigger() ){
                        rule.getAction().executeAction();
                    } 
                }
               
           }
          try {
                // Dormi per 5 secondi
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
