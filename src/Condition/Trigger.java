package Condition;

import java.util.List;

/**
 *
 * @author Simone
 */
public class Trigger {
    
    
    private Condition condition;

    public Trigger(Condition condition) {
        this.condition = condition;
    }
 
    
    
    public boolean checkTrigger(){
        return condition.checkCondition();
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Trigger{" + "condition=" + condition.toString() + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
