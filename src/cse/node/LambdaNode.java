package cse.node;

import cse.CSEMachine;
import cse.Environment;

import java.util.ArrayList;

public class LambdaNode implements CSENode {
    private int index;
    private ArrayList<String> boundVariables;
    private Environment env;

    public LambdaNode(int index, ArrayList<String> boundVariables, Environment env) {
        this.index = index;
        this.boundVariables = boundVariables;
        this.env = env;
    }

    public LambdaNode(int index, ArrayList<String> boundVariable) {
        this.index = index;
        this.boundVariables = boundVariable;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "LambdaNode{" +
                "index=" + index +
                ", boundVariable='" + boundVariables + '\'' +
                '}';
    }

    /***
     * Set the currentEnviroment of the CSEMachine to the LambdaNode's currentEnv and add LambdaNode to stack.
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        this.setEnv(cseMachine.getCurrentEnv());
        cseMachine.getStack().add(0,this);
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public Environment getEnv() {
        return env;
    }

    public ArrayList<String> getBoundVariable() {
        return boundVariables;
    }
}
