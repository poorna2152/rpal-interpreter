package cse.node;

import cse.CSEMachine;

import java.util.ArrayList;

public class TupleNode implements CSENode {
    private ArrayList<CSENode> children;

    public TupleNode(ArrayList<CSENode> children) {
        this.children = children;
    }

    public void setChildren(ArrayList<CSENode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TupleNode{" +
                "children=" + children +
                '}';
    }


    public ArrayList<CSENode> getChildren() {
        return children;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        System.out.println("Error");
    }
}
