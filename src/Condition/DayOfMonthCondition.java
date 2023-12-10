package Condition;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Questa classe implementa l'interfaccia @see Condition e rappresenta una condizione basata sul giorno del mese.
 * La classe è anche serializzabile.
 * @author Simone Pacifico
 */
public class DayOfMonthCondition implements Condition, Serializable{
    private Integer day;
    private boolean checkedToday;
    private boolean not;
    Integer lastCheck;
    
    /**
     * Costruttore della classe. 
     * Inizializza l'istanza con il giorno specificato e il flag di negazione.
     * 
     * @param day il giorno del mese su cui la condizione deve essere verificata.
     * @param not flag che indica se la condizione deve essere negata.
     */
    
    public DayOfMonthCondition(Integer day, boolean not) {
        this.day = day;
        this.not = not;
        lastCheck = LocalDate.now().getDayOfMonth();
    }
    
    /**
     * Verifica se la condizione è attualmente soddisfatta in base al giorno del mese corrente.
     * 
     * @return true se la condizione è soddisfatta, false altrimenti.
     */
    
    @Override
    public boolean checkCondition() {
        boolean exit;
        LocalDate currentDate = LocalDate.now();
        // Get the current day as integers
        Integer now = currentDate.getDayOfMonth();
        boolean cond = day.equals(now);
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
     * Resetta lo stato della condizione, segnando che è stata verificata oggi.
     */
    @Override
    public void resetState() {
        this.checkedToday = true;
    }
    
}
