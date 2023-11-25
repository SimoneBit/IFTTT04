package Condition;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * La classe TimeOfDay Condition implementa l'interfaccia @see Condition e rappresenta
 * una condizione basata sull'orario del giorno. La condizione è considerata vera solo se l'orario
 * attuale corrisponde all'orario specificato durante la creazione dell'istanza della classe.
 * Inoltre, la condizione è verificata una sola volta al giorno.
 * 
 * @author Palma Orlando
 * 
 */
public class TimeOfDayCondition implements Condition {
    
    /**L'orario specificato per la condizione.*/
    private LocalTime specifiedTime;  
/** Flag che indica se la condizione è stata verificata in giornata */    
    private boolean checkedToday;
    
    
 /**
  * Costruisce un'istanza di TimeOfDayCondition con l'orario specificato.
  * @param orarioSpecificato stringa rappresentante l'orario nel formato "HH:mm".
  */   
    public TimeOfDayCondition(String orarioSpecificato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.specifiedTime = LocalTime.parse(orarioSpecificato, formatter);
        this.checkedToday = false;
    }

    
    /**
     * Verifica se la condizione è attualmente soddisfatta. La condizione è considerata vera
     * solo se l'orario attuale corrisponde all'orario specificato durante la creazione
     * dell'istanza della classe, e la verifica avviene una sola volta al giorno.
     * @return true se la condizione è soddisfatta, altrimenti false.
     */
    @Override
    public boolean checkCondition() {
        boolean cond = LocalTime.now().truncatedTo(ChronoUnit.MINUTES).equals(specifiedTime);
        if (cond && !checkedToday){
            checkedToday = true;
            return true;
        }
        if(!cond){
            checkedToday = false;
        }
        return false;
    }

    
    /**
     * Restituisce una rappresentazione stringa dell'oggetto TimeOfDayCondition, mostrando l'orario specificato.
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "TimeOfDayCondition{" + "specifiedTime=" + specifiedTime + '}';
    }
    
    
}
