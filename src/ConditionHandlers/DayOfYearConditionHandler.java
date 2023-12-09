package ConditionHandlers;

import Condition.*;

/**
 *
 * @author Simone Pacifico
 */
public class DayOfYearConditionHandler extends BaseConditionHandler{
    
    
    @Override
    public Condition handle(String request, String param, boolean logic) {
        if(request.compareTo("Il") == 0){
            String []values = param.split("/");
            Integer day = Integer.valueOf(values[0]);
            Integer month = Integer.valueOf(values[1]);
            Condition c = new DayOfYearCondition(month, day, logic);
            return c;
        }else{
            if(next != null){
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
}
