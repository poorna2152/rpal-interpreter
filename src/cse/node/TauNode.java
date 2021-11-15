package cse.node;

import cse.CSEMachine;

import java.util.ArrayList;

public class TauNode implements CSENode {
    private int childrenCount;

    @Override
    public String toString() {
        return "TauNode{" +
                "childrenCount=" + childrenCount +
                '}';
    }


    public TauNode(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    /***
     * Add the next childrenCount number of elements in the stack to a TupleNode and add TupleNode to the stack.
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        ArrayList<CSENode> children = new ArrayList<>();
        for (int i = 0; i < childrenCount; i++) {
            CSENode node =  cseMachine.getStack().remove(0);
            children.add(node);
        }
        cseMachine.getStack().add(0,new TupleNode(children));
    }

}
