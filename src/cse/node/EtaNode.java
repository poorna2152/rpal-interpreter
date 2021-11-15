package cse.node;

import cse.CSEMachine;
import cse.Environment;

import java.util.ArrayList;

public class EtaNode implements CSENode{
    private int index;
    private ArrayList<String> boundVariables;
    private Environment env;

    public EtaNode( int index, ArrayList<String> boundVariable, Environment env) {
        this.index = index;
        this.boundVariables = boundVariable;
        this.env = env;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "EtaNode{" +
                "index=" + index +
                ", boundVariable='" + boundVariables + '\'' +
                '}';
    }

    /***
     * ETANode cannot be in the control
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        System.out.println("Invalid eta node found in control");
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
