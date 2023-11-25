package Rule;

/**
 *La classe CheckRules estende Thread e rappresenta un thread per la verifica periodica delle regole presenti in 
 * un insieme specificato.
 * 
 * Il thread esegue un ciclo infinito, verificando periodicamente lo stato delle regole attive e, se la condizione del trigger 
 * di una regola è soddisfatta, esegue l'azione associata a quella regola.
 * @author Miriam Vitolo
 */

public class checkRules extends Thread {
    
    private RulesSet r;
    
    /**
     * Costruisce un'istanza di @see CheckRules associata a un insieme di regole specificato.
     *
     * @param rs l'insieme di regole da verificare.
     */
    public checkRules(RulesSet rs){
        this.r= rs;
    }
    
    /**
     * Implementazione del metodo @see run che rappresenta il corpo del thread. Il thread eseguirà un ciclo infinito, 
     * verificando periodicamente lo stato delle regole ed eseguendo le azioni associate se la condizione
     * del trigger è soddisfatta.
     */
    @Override
    public void run() {
        // Ciclo infinito, il thread continua ad eseguirsi indefinitamente
        while (true) { 
            System.out.println("Thread vivo");
            for (Rule rule: r.getRuleList()){
                if(rule.isActive()){
                    if (rule.getTrigger().checkTrigger() ){
                        rule.getAction().executeAction();
                    } 
                }
               
           }
          try {
                // Dormi per 5 secondi
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
