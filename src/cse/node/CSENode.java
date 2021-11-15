package cse.node;

import cse.CSEMachine;

public interface CSENode {
    /***
     * Each node in the CSE implement the CSENode interface.
     * NODES in control and stack are CSENodes
     * Set the evaluated result to the CSEMachine (control and stack) after this node is popped and the function is called.
     * @param cseMachine
     */
    public abstract void evaluate(CSEMachine cseMachine);
}
