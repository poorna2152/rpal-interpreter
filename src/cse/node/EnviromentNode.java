package cse.node;

import cse.CSEMachine;
import cse.Environment;

import java.util.ArrayList;

public class EnviromentNode implements CSENode{
    private Environment environment;

    public EnviromentNode(Environment environment) {
        this.environment = environment;
    }

    /***
     * Remove the EnviromentNode in the second position of the stack.
     * Check the control of CSEMachine from end to start for an EnviromentNode and set the currentEnvironmnent of
     * the CSEMachine to the
     * closest EnvironmentNode in the control to the end.(rightmost)
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().remove(1);
        ArrayList<CSENode> control = cseMachine.getControl();
        Environment nextEnv = null;
        int index = control.size()-1;
        boolean found = false;
        while(!found && index >= 0){
            CSENode node = control.get(index);
            if(node instanceof EnviromentNode){
                found = true;
                nextEnv = ((EnviromentNode) node).getEnvironment();
            }
            index--;
        }
        cseMachine.setCurrentEnv(nextEnv);
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "EnviromentNode{" +
                "environment=" + environment.getNames() +
                '}';
    }
}
