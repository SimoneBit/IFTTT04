package Condition;

import java.io.File;
import java.io.Serializable;

/**
 * La classe FileExistenceCondition implementa l'interfaccia Condition e rappresenta
 * una condizione basata sull'esistenza di un file in una cartella specifica.
 * La condizione è considerata vera solo se il file specificato esiste nella cartella specificata.
 * 
 * 
 * @author: Nicola Lanzara
 */
public class FileExistenceCondition implements Condition, Serializable {

    /** Il percorso della cartella in cui cercare il file. */
    private String folderPath;

    /** Il nome del file da cercare. */
    private String fileName;
    private boolean not;

    /**
     * Costruisce un'istanza di FileExistenceCondition con il percorso della cartella e il nome del file specificati.
     * @param folderPath il percorso della cartella.
     * @param fileName il nome del file.
     * @param not flag che indica se la condizione deve essere negata.
     */
    public FileExistenceCondition(String folderPath, String fileName, boolean not) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        this.not = not;
    }

    /**
     * Verifica se la condizione è attualmente soddisfatta. La condizione è considerata vera
     * solo se il file specificato esiste nella cartella specificata.
     * @return true se la condizione è soddisfatta, altrimenti false.
     */
    @Override
    public boolean checkCondition() {
        File folder = new File(folderPath);
        File file = new File(folder, fileName);
        boolean cond = file.exists();
        return cond ^ not;
    }

    /**
     * Restituisce una rappresentazione stringa dell'oggetto FileExistenceCondition.
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "FileExistenceCondition{" +
                "folderPath='" + folderPath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
    
    /**
     * Resetta lo stato della condizione, segnando che è stata verificata oggi.
     */
    @Override
    public void resetState() {
        return;
    }
}
