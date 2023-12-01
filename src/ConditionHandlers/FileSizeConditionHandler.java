package ConditionHandlers;

import Condition.*;

/**
 * La classe FileSizeConditionHandler implementa l'interfaccia ConditionHandler e gestisce
 * la creazione dell'oggetto FileSizeCondition.
 * 
 * 
 * @author Nicola Lanzara
 */
public class FileSizeConditionHandler extends BaseConditionHandler {

    @Override
    public Condition handle(String request, String param) {
        if (request.equals("Dimensione del file")) {
            // Creare e restituire un'istanza di FileSizeCondition
            try {
                String[] params = param.split(" ha dimensione minore di: ");
                String filePath = params[0];
                long fileSize = Long.parseLong(params[1]);
                System.out.println("Param 0: " +params[0]);
                System.out.println("Param 1: " +params[1]);

                return new FileSizeCondition(filePath, fileSize);
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