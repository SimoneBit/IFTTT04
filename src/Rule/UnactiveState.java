/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Rule;

import java.io.Serializable;

/**
 *
 * @author Simone
 */
public class UnactiveState implements ActivationState, Serializable {

    @Override
    public boolean executeRule(Rule rule) {
        return true;
    }

    @Override
    public boolean checkRule(Rule rule) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
    
}
