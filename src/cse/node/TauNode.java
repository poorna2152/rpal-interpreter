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
