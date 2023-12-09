package ConditionHandlers;

import Condition.Condition;
import Condition.DayOfMonthCondition;

/**
 *
 * @author Simone Pacifico
 */
public class DayOfMonthConditionHandler extends BaseConditionHandler {
    @Override
    public Condition handle(String request, String param, boolean logic){
        if(request.compareTo("Il giorno") == 0){
            Integer day = Integer.valueOf(param);
            Condition c = new DayOfMonthCondition(day, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
}
