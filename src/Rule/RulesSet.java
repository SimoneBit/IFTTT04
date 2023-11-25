/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicola
 */

public class RulesSet{
    private List<Rule> ruleList;

    public RulesSet() {
        this.ruleList = new ArrayList<>();
    }

    public void addRule(Rule newRule) {
        
        ruleList.add(newRule);
    }

    
    public void removeRule(Rule rule) {
        
        ruleList.remove(rule);
    }
    
    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

  

    
}
