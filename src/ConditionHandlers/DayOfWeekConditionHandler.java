package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfWeekCondition;

/**
 *
 * @author Palma Orlando
 */
public class DayOfWeekConditionHandler extends BaseConditionHandler{

    @Override
    public Condition handle(String request, String param, boolean logic) {
        if(request.compareTo("Il giorno della settimana è") == 0){
            Condition c = new DayOfWeekCondition(param, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param,logic);
            }
        }
        return null;
    }
    
}
