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
        if (request.equals("Il file")) {
            // Creare e restituire un'istanza di FileExistenceCondition
            try {
                String[] params = param.split(" esiste nella cartella: ");
                String folderPath = params[1];
                String fileName = params[0];
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
