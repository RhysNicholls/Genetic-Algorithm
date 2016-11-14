/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithmassignmentt;

import java.util.Arrays;

/**
 *
 * @author Rhys
 */
public class Rule {
    
    private byte[] variables;
    private byte outcome;

    public Rule(byte[] variables, byte outcome) {
        this.variables = variables;
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return  "Variables =" + Arrays.toString(variables) + ", Outcome = " + outcome ;
    }

    public byte[] getVariables() {
        return variables;
    }
     public int getVariable(int index) {
        return variables[index];
    }

    public void setVariables(byte[] variables) {
        this.variables = variables;
    }

    public byte getOutcome() {
        return outcome;
    }

    public void setOutcome(byte outcome) {
        this.outcome = outcome;
    }
    
    
}
