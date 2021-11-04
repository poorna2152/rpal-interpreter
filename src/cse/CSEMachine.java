package cse;

import cse.node.CSENode;
import cse.node.EnviromentNode;
import cse.sttraverser.PreOrderTraverser;
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
        System.out.println("CSE Machine");
        System.out.println("");
        this.evaluate();
    }

    public void evaluate(){
        CSENode poppedValue = null;
        int count =  0;
        while(control.size()  > 0){
            poppedValue = control.remove(control.size()-1);
//            System.out.println("New Val");
//            System.out.println(poppedValue);
            poppedValue.evaluate(this);
//            System.out.println("Stack");
//            System.out.println(stack);
//            System.out.println("Control");
//            System.out.println(control);
//            System.out.println("");
            count++;
        }
        System.out.println(stack.remove(0));
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

    public void addToControlStructure(ArrayList<ArrayList<CSENode>> nodes){
        this.controlStructures.addAll(nodes);
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


    public CSENode lookUp(String symbol){
        Environment env = currentEnv;
        boolean found = false;
        CSENode val = null;
        System.out.println("checking");
        while(!found && env != null){
            System.out.println(env);
            found = env.checkForExistence(symbol);
            if(found)
                val = env.getValue(symbol);
            env = env.getParentEnviroment();
        }
        System.out.println("checking over");
        return val;
    }
}
