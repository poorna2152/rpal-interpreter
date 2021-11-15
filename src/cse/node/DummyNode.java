package cse.node;

import cse.CSEMachine;

public class DummyNode implements CSENode {
    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().add(0,this);
    }

    @Override
    public String toString() {
        return "DummyNode";
    }
}
