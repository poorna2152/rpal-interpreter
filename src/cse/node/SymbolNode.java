package cse.node;

import cse.CSEMachine;
import definitions.*;

public class SymbolNode implements CSENode {
    private String label;

    public SymbolNode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        OperationHandler.getInstance().operateSymbol(this.label,cseMachine);
    }

}
