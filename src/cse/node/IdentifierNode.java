package cse.node;

import cse.CSEMachine;
import definitions.OperationHandler;

public class IdentifierNode implements CSENode{
    private String label;


    public IdentifierNode(String label) {
        this.label = label;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        CSENode symbol = cseMachine.lookUp(this.label);
        if(symbol != null){
            cseMachine.getStack().add(0, symbol);
        }
        else{
            cseMachine.getStack().add(0, this);

        }

    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "IdentifierNode{" +
                "label='" + label + '\'' +
                '}';
    }
}
