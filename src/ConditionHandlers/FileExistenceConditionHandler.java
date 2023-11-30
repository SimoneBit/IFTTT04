package ConditionHandlers;

import Condition.Condition;
import Condition.FileExistenceCondition;

/**
 * La classe FileExistenceConditionHandler implementa l'interfaccia ConditionHandler e gestisce
 * la creazione dell'oggetto FileExistenceCondition.
 * Aggiungi questa classe alla catena delle responsabilità per gestire la creazione
 * dell'oggetto FileExistenceCondition.
 * 
 * Come utente voglio specificare il nome di un file e una cartella
 * in modo da poter controllare se esiste il file nella cartella
 * e utilizzarlo come condizione di un trigger.
 * 
 * @author : Nicola Lanzara
 */
public class FileExistenceConditionHandler extends BaseConditionHandler {

    @Override
    public Condition handle(String request, String param) {
        if (request.equalsIgnoreCase("FileExistence")) {
            // Creare e restituire un'istanza di FileExistenceCondition
            try {
                String[] params = param.split(",");
                String folderPath = params[0].trim();
                String fileName = params[1].trim();
                return new FileExistenceCondition(folderPath, fileName);
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
