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
    
    private LocalTime specifiedTime;  
    private boolean checkedToday;
    private LocalTime lastCheck;
    private boolean not;
    
    
 /**
 * Inizializza un'istanza della classe con l'orario specificato e il flag di negazione specificato.
 * L'orario specificato deve essere nel formato "HH:mm", e viene convertito in un oggetto LocalTime.
 * Il flag di negazione indica se la condizione deve essere negata.
 * L'orario dell'ultima verifica viene inizializzato con l'orario attuale troncato ai minuti.
 * 
 * @param orarioSpecificato l'orario specificato nel formato "HH:mm".
 * @param not flag che indica se la condizione deve essere negata.
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
    
    /**
     * Resetta lo stato della condizione, segnando che è stata verificata oggi.
     */
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
}
