package Condition;

import java.io.Serializable;
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
public class TimeOfDayCondition implements Condition, Serializable {
    
    /**L'orario specificato per la condizione.*/
    private LocalTime specifiedTime;  
    /** Flag che indica se la condizione è stata verificata in giornata */    
    private boolean checkedToday;
    private LocalTime lastCheck;
    private boolean not;
    
    
 /**
  * Costruisce un'istanza di TimeOfDayCondition con l'orario specificato.
  * @param orarioSpecificato stringa rappresentante l'orario nel formato "HH:mm".
  */   
    public TimeOfDayCondition(String orarioSpecificato, boolean not) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.specifiedTime = LocalTime.parse(orarioSpecificato, formatter);
        this.not = not;
        this.lastCheck = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    
    /**
     * Verifica se la condizione è attualmente soddisfatta. La condizione è considerata vera
     * solo se l'orario attuale corrisponde all'orario specificato durante la creazione
     * dell'istanza della classe, e la verifica avviene una sola volta al giorno.
     * @return true se la condizione è soddisfatta, altrimenti false.
     */
    @Override
    public boolean checkCondition() {
        boolean exit;
        LocalTime now = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        boolean cond = now.equals(specifiedTime);
        if(!cond){
            if (!lastCheck.equals(now)){
                checkedToday = false;
                lastCheck = now;
            }
        }
        if (!checkedToday && (cond ^ not)){
            exit = true;
        }else{
            exit = false;
            if(!(cond ^ not)){
                checkedToday = false;
            }
        }
        return exit;
    }

    
    /**
     * Restituisce una rappresentazione stringa dell'oggetto TimeOfDayCondition, mostrando l'orario specificato.
     * @return una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "TimeOfDayCondition{" + "specifiedTime=" + specifiedTime + '}';
    }
    
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
}
