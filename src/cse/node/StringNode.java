package cse.node;

import cse.CSEMachine;

public class StringNode implements CSENode {
    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().add(0,this);
    }

    @Override
    public String toString() {
        return value;
    }
}
