package Condition;

import java.util.List;

/**
 *
 * @author Simone
 */
public class Trigger {
    
    
    private ConditionHandler condition;

    public Trigger(ConditionHandler condition) {
        this.condition = condition;
    }
 
    
    
    public boolean checkTrigger(){
        return condition.checkCondition();
    }

    public ConditionHandler getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Trigger{" + "condition=" + condition.toString() + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
