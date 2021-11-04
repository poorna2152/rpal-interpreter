package cse.node;

import cse.CSEMachine;

public class BooleanNode implements CSENode {
    private boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().add(0,this);
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return  Boolean.toString(value);
    }
}
