package Condition;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * Questa classe implementa l'interfaccia @see Condition e rappresenta una condizione basata sul giorno dell'anno.
 * La classe è anche serializzabile.
 * @author Simone Pacifico
 */
public class DayOfYearCondition implements Condition, Serializable {
    private Integer month;
    private Integer day;
    private Integer lastMonthChecked;
    private Integer lastDayChecked;
    private boolean checkedToday;
    private boolean not;

    /**
     * Costruttore della classe. 
     * Inizializza l'istanza con il mese, il giorno e il flag di negazione specificati.
     * 
     * @param month il mese su cui la condizione deve essere verificata.
     * @param day il giorno del mese su cui la condizione deve essere verificata.
     * @param not flag che indica se la condizione deve essere negata.
     */
    public DayOfYearCondition(Integer month, Integer day, boolean not) {
        this.month = month;
        this.day = day;
        this.not = not;
        LocalDate currentDate = LocalDate.now();
        lastMonthChecked = currentDate.getMonthValue();
        lastDayChecked = currentDate.getDayOfMonth();
        
    }

    /**
     * Verifica se la condizione è attualmente soddisfatta in base al giorno dell'anno corrente.
     * 
     * @return true se la condizione è soddisfatta, false altrimenti.
     */
    @Override
    public boolean checkCondition() {
        boolean exit;
        LocalDate currentDate = LocalDate.now();
        // Get the current day and month as integers
        Integer currentMonth = currentDate.getMonthValue();
        Integer currentDay = currentDate.getDayOfMonth();
        
        
        boolean cond = month.equals(currentMonth) && day.equals(currentDay);
        if(!cond){
            if (!lastMonthChecked.equals(currentMonth) || !lastDayChecked.equals(currentDay)){
                checkedToday = false;
                lastMonthChecked = currentMonth;
                lastDayChecked = currentDay;
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
