package cse.node;

import cse.CSEMachine;

public class IntegerNode implements CSENode {
    private int value;

    public IntegerNode(int value) {
        this.value = value;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().add(0,this);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
