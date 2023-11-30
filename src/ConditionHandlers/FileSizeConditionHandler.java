package ConditionHandlers;

import Condition.*;

/**
 * La classe FileSizeConditionHandler implementa l'interfaccia ConditionHandler e gestisce
 * la creazione dell'oggetto FileSizeCondition.
 * Aggiungi questa classe alla catena delle responsabilità per gestire la creazione
 * dell'oggetto FileSizeCondition.
 * 
 * @author Nicola Lanzara
 */
public class FileSizeConditionHandler extends BaseConditionHandler {

    @Override
    public Condition handle(String request, String param) {
        if (request.equalsIgnoreCase("FileSize")) {
            // Creare e restituire un'istanza di FileSizeCondition
            try {
                String[] params = param.split(",");
                String filePath = params[0].trim();
                long fileSize = Long.parseLong(params[1].trim());


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