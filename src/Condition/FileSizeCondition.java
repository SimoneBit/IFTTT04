
package Condition;

import java.io.File;
import java.io.Serializable;

/**
 * La classe FileSizeCondition implementa l'interfaccia Condition e rappresenta
 * una condizione basata sulla dimensione di un file. La condizione è considerata
 * vera solo se la dimensione del file specificato durante la creazione dell'istanza
 * della classe supera la dimensione specificata.
 * 
 * @author: Nicola Lanzara
 */
public class FileSizeCondition implements Condition, Serializable {
    
    /** Il percorso del file per la condizione. */
    private String filePath;
    /** La dimensione minima del file per soddisfare la condizione. */
    private long minSize;
    private boolean not;

    /**
     * Costruisce un'istanza di FileSizeCondition con il percorso del file e la dimensione minima specificati.
     * 
     * @param filePath percorso del file.
     * @param minSize dimensione minima del file.
     * @param not flag che indica se la condizione deve essere negata.
     */
    public FileSizeCondition(String filePath, long minSize, boolean not) {
        this.filePath = filePath;
        this.minSize = minSize * 1024; // trasformo il valore minimo dato da KB a Byte
        this.not = not;
    }

    /**
     * Verifica se la condizione è attualmente soddisfatta. La condizione è considerata vera
     * solo se la dimensione del file specificato supera la dimensione minima specificata durante
     * la creazione dell'istanza della classe.
     * 
     * @return true se la condizione è soddisfatta, altrimenti false.
     */
    @Override
    public boolean checkCondition() {
        File file = new File(filePath);
        if (file.exists()) {
            long fileSize = file.length();
            boolean cond = fileSize > minSize;
            return cond ^ not;
        } else {
            return false;  // il file non esite
        }
    }

    /**
     * Restituisce una rappresentazione stringa dell'oggetto FileSizeCondition, mostrando il percorso del file
     * e la dimensione minima specificati.
     * 
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "FileSizeCondition{" + "filePath='" + filePath + '\'' + ", minSize=" + minSize + '}';
    }
    
    /**
     * Resetta lo stato della condizione, segnando che è stata verificata oggi.
     */
    @Override
    public void resetState() {
        return;
    }
}