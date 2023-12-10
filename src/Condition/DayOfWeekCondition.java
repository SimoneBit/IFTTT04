package Condition;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Questa classe implementa l'interfaccia @see Condition e rappresenta una condizione basata sul giorno della 
 * settimana. La classe è anche serializzabile.
 * @author Palma Orlando
 */
public class DayOfWeekCondition implements Condition, Serializable {
    private String daySelected;
    private boolean checkedToday;
    private boolean not;
    DayOfWeek lastCheck;

    /**
     * Costruttore della classe. 
     * Inizializza l'istanza con il giorno specificato e il flag di negazione.
     * 
     * @param daySelected il giorno della settimana su cui la condizione deve essere verificata.
     * @param not flag che indica se la condizione deve essere negata.
     */
    public DayOfWeekCondition(String daySelected, boolean not) {
        this.daySelected = daySelected;
        this.not = not;
        lastCheck = LocalDate.now().getDayOfWeek();
    }

    /**
     * Verifica se la condizione è attualmente soddisfatta in base al giorno della settimana corrente.
     * 
     * @return true se la condizione è soddisfatta,  false altrimenti.
     */
    @Override
    public boolean checkCondition(){
        boolean exit;
        String dayUpperCase = daySelected.toUpperCase();
        // Ottenere il giorno della settimana corrente
        DayOfWeek now = LocalDate.now().getDayOfWeek();

        // Confronto l'input con il giorno della settimana corrente
        boolean cond = dayUpperCase.equals(now.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase());
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