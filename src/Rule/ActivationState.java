/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Rule;

/**
 *
 * @author Simone
 */
public interface ActivationState {
    
    public boolean executeRule(Rule rule);
    public boolean checkRule(Rule rule);
    public boolean isActive();
}
