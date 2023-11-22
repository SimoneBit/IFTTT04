/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 39349
 */
public class GestoreRegole {
    private List<Rule> elencoRegole;

    public GestoreRegole() {
        this.elencoRegole = new ArrayList<>();
    }

    // Metodo per aggiungere una nuova regola
    public void aggiungiRegola(Rule nuovaRegola) {
        
        this.elencoRegole.add(nuovaRegola);
    }

    
    public List<Rule> getElencoRegole() {
        return this.elencoRegole;
    }
    
    public void setElencoRegole(List<Rule> elencoRegole) {
        this.elencoRegole = elencoRegole;
    }
    
}
