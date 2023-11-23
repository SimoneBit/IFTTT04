package Condition;

import java.util.List;

/**
 *
 * @author Simone
 */
public class Trigger {
    
    
    private String name;
    private Condition condition;
 
    //
    
    public boolean checkTrigger(){
        return condition.checkCondition();
    }

    public Condition getCondition() {
        return condition;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
