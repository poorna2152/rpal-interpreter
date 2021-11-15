package cse.node;

import cse.CSEMachine;

import java.util.ArrayList;

public class ConditionalNode implements CSENode {
    private ArrayList<ArrayList<CSENode>> thenControls;
    private ArrayList<ArrayList<CSENode>> elseControls;

    public ConditionalNode() {
        this.thenControls = new ArrayList<>();
        this.elseControls = new ArrayList<>();
    }

    public void setThenControls(ArrayList<ArrayList<CSENode>> thenControls) {
        this.thenControls = thenControls;
    }

    public void setElseControls(ArrayList<ArrayList<CSENode>> elseControls) {
        this.elseControls = elseControls;
    }

    /***
     *
     * Remove the first element of the stack and check if it is true or false.
     * If true add thenControls to controlStructure
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        BooleanNode boolResult = (BooleanNode) cseMachine.getStack().remove(0);
        if(boolResult.getValue()){
            cseMachine.addToControlStructure(thenControls);
            cseMachine.addToControl(thenControls.get(0));
        }
        else{
            cseMachine.addToControlStructure(elseControls);
            cseMachine.addToControl(elseControls.get(0));
        }
    }

    @Override
    public String toString() {
        return "ConditionalNode";
    }
}
