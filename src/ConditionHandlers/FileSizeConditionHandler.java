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
    public Condition handle(String request, String param, boolean logic) {
        if (request.equals("Il file selezionato")) {
            // Creare e restituire un'istanza di FileSizeCondition
            try {
                String[] params = param.split(" ha dimensione maggiore di: ");
                String filePath = params[0];
                long fileSize = Long.parseLong(params[1]);
                return new FileSizeCondition(filePath, fileSize, logic);
            } catch (Exception e) {
                // Gestisci eventuali errori durante la creazione dell'istanza
                e.printStackTrace();
            }
        } else {
            if (next != null) {
                return next.handle(request, param, logic);
            }
        }
        return null;
    }
}