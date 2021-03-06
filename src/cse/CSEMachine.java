package cse;

import standardize.STNode;

import java.util.ArrayList;

public class CSEMachine {
    private Environment currentEnv;
    private ArrayList<ArrayList<CSENode>> controlStructures;
    private ArrayList<CSENode> control = new ArrayList<>();
    private ArrayList<CSENode> stack = new ArrayList<>();

    public CSEMachine(STNode root) {
        PreOrderTraverser preOrderTraverser = new PreOrderTraverser(root);
        this.controlStructures = preOrderTraverser.startTraversal();
        this.currentEnv = new Environment(null);
        EnviromentNode e0 = new EnviromentNode(currentEnv);
        this.control.add(e0);
        this.control.addAll(this.controlStructures.get(0));
        this.stack.add(e0);
        this.evaluate();
    }

    public void evaluate(){
        CSENode poppedValue = null;
        while(control.size()  > 0){
            poppedValue = control.get(control.size()-1);
            control.remove(control.size()-1);
            poppedValue.evaluate(this);
            System.out.println(control);
        }
    }

    public void setCurrentEnv(Environment currentEnv) {
        this.currentEnv = currentEnv;
    }

    public void setControl(ArrayList<CSENode> control) {
        this.control = control;
    }

    public void addToControl(CSENode node){
        this.control.add(node);
    }

    public void addToControl(ArrayList<CSENode> nodes){
        this.control.addAll(nodes);
    }

    public void setStack(ArrayList<CSENode> stack) {
        this.stack = stack;
    }

    public ArrayList<ArrayList<CSENode>> getControlStructures() {
        return controlStructures;
    }

    public Environment getCurrentEnv() {
        return currentEnv;
    }

    public ArrayList<CSENode> getControl() {
        return control;
    }

    public ArrayList<CSENode> getStack() {
        return stack;
    }


    public String lookUp(String symbol){
        Environment env = currentEnv;
        boolean found = false;
        String val = null;
        while(!found && env != null){
            found = env.checkForExistence(symbol);
            if(found)
                val = env.getValue(symbol);
            env = env.getParentEnviroment();
        }
        return val;
    }
}
