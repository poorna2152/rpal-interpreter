package cse;

import java.util.ArrayList;

public class ConditionalNode implements CSENode {
    private ArrayList<CSENode> thenControls;
    private ArrayList<CSENode> elseControls;

    public ConditionalNode() {
        this.thenControls = new ArrayList<>();
        this.elseControls = new ArrayList<>();
    }

    public ArrayList<CSENode> getThenControls() {
        return thenControls;
    }

    public ArrayList<CSENode> getElseControls() {
        return elseControls;
    }

    public void setThenControls(ArrayList<CSENode> thenControls) {
        this.thenControls = thenControls;
    }

    public void setElseControls(ArrayList<CSENode> elseControls) {
        this.elseControls = elseControls;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        System.out.println("Conditional node");
    }

    @Override
    public String toString() {
        return "ConditionalNode{" +
                "thenControls=" + thenControls +
                ", elseControls=" + elseControls +
                '}';
    }
}
