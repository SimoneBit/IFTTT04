
package Condition;

import java.io.File;

/**
 * La classe FileSizeCondition implementa l'interfaccia Condition e rappresenta
 * una condizione basata sulla dimensione di un file. La condizione è considerata
 * vera solo se la dimensione del file specificato durante la creazione dell'istanza
 * della classe supera la dimensione specificata.
 * 
 * @author: Nicola Lanzara
 */
public class FileSizeCondition implements Condition {
    
    /** Il percorso del file per la condizione. */
    private String filePath;
    /** La dimensione minima del file per soddisfare la condizione. */
    private long minSize;

    /**
     * Costruisce un'istanza di FileSizeCondition con il percorso del file e la dimensione minima specificati.
     * 
     * @param filePath percorso del file.
     * @param minSize dimensione minima del file.
     */
    public FileSizeCondition(String filePath, long minSize) {
        this.filePath = filePath;
        this.minSize = minSize *1024;
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
        long fileSizeKB = file.length() / 1024;
        return fileSizeKB > minSize;
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
}