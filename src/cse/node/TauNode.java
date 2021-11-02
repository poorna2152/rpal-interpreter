package cse.node;

import cse.CSEMachine;

import java.util.ArrayList;

public class TauNode implements CSENode {
    private int childrenCount;
    private ArrayList<CSENode> children = new ArrayList<>();

    @Override
    public String toString() {
        return "TauNode{" +
                "childrenCount=" + childrenCount +
                ", children=" + children +
                '}';
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public TauNode(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        for (int i = 0; i < childrenCount; i++) {
            SymbolNode node =  (SymbolNode)cseMachine.getStack().remove(0);
            children.add(node);
        }
        cseMachine.getStack().add(0,this);
    }

    public ArrayList<CSENode> getChildren() {
        return children;
    }
}
