/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simone
 */
public class RulesSet extends ArrayList<Rule>{
    private List<Rule> ruleList;

    public RulesSet() {
        this.ruleList = new ArrayList<>();
    }

    public void addRule(Rule newRule) {
        
        this.ruleList.add(newRule);
    }

    
    public List<Rule> getRuleList() {
        return this.ruleList;
    }
    
    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
