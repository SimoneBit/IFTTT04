package ConditionHandlers;

import Condition.*;

/**
 *
 * @author Palma Orlando
 */
public class ExitStatusConditionHandler extends BaseConditionHandler {

    @Override
    public Condition handle(String request, String param) {
        if (request.equals("Controlla il valore")) {
            // Creare e restituire un'istanza di ExitStatusCondition
            try {
                String[] params = param.split(" Valore atteso: ");
                System.out.println("Param 0: " +params[0]);
                System.out.println("Param 1: " +params[1]);
                String programPath = params[0];
                int expectedValue = Integer.parseInt(params[1].trim()) ;
                
                System.out.println("Path: " +programPath);
                System.out.println("int: " +expectedValue);

                return new ExitStatusCondition(programPath, expectedValue);
            } catch (Exception e) {
                // Gestisci eventuali errori durante la creazione dell'istanza
                e.printStackTrace();
            }
        } else {
            if (next != null) {
                return next.handle(request, param);
            }
        }
        return null;
    }
    
    
}

