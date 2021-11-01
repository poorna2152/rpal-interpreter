package cse;

import java.util.ArrayList;

public class ConditionalNode implements CSENode {
    private ArrayList<ArrayList<CSENode>> thenControls;
    private ArrayList<ArrayList<CSENode>> elseControls;

    public ConditionalNode() {
        this.thenControls = new ArrayList<>();
        this.elseControls = new ArrayList<>();
    }

    public ArrayList<ArrayList<CSENode>> getThenControls() {
        return thenControls;
    }

    public ArrayList<ArrayList<CSENode>> getElseControls() {
        return elseControls;
    }

    public void setThenControls(ArrayList<ArrayList<CSENode>> thenControls) {
        this.thenControls = thenControls;
    }

    public void setElseControls(ArrayList<ArrayList<CSENode>> elseControls) {
        this.elseControls = elseControls;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        SymbolNode boolResult = (SymbolNode)cseMachine.getStack().remove(0);
        if(boolResult.getLabel() =="true"){
            System.out.println("chose then");
            cseMachine.addToControlStructure(thenControls);
            cseMachine.addToControl(thenControls.get(0));
        }
        else{
            System.out.println("chose else");
            System.out.println("else structure");
            System.out.println(elseControls);
            cseMachine.addToControlStructure(elseControls);
            cseMachine.addToControl(elseControls.get(0));
        }
    }

    @Override
    public String toString() {
        return "ConditionalNode{" +
                "thenControls=" + thenControls +
                ", elseControls=" + elseControls +
                '}';
    }
}
