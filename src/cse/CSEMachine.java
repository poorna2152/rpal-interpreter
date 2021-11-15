package cse;

import cse.node.CSENode;
import cse.node.EnviromentNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class CSEMachine {
    private cse.Environment currentEnv;
    private ArrayList<ArrayList<CSENode>> controlStructures;
    private ArrayList<CSENode> control = new ArrayList<>();
    private ArrayList<CSENode> stack = new ArrayList<>();
    private STNode root;
    private boolean printControl = false;

    public CSEMachine(STNode root, boolean printControl) {
        this.root = root;
        this.printControl = printControl;
    }

    /***
     * Generate control structures and start the evaluation of the CSE Machine.
     * PreOrder traverser generate the Control structures and the currentEnv set to null
     */
    public void startEvaluate(){
        PreOrderTraverser preOrderTraverser = new PreOrderTraverser(root, printControl);
        this.controlStructures = preOrderTraverser.startTraversal();
        this.currentEnv = new cse.Environment(null);
        EnviromentNode e0 = new EnviromentNode(currentEnv);
        this.control.add((CSENode) e0);
        this.control.addAll(this.controlStructures.get(0));
        this.stack.add((CSENode) e0);
        this.evaluate();
    }

    /***
     * Evaluate a single node in the Control. Control is popped and the popped node is evaluated.
     */
    public void evaluate(){
        CSENode poppedValue = null;
        while(this.control.size()  > 0 ){
            poppedValue = control.remove(this.control.size()-1);
            poppedValue.evaluate(this);
        }
    }

    /***
     * Look up the definition of a symbol in the environment structure.
     * From the current env to upto the parent env and so on.
     * @param symbol Symbol to lookup
     * @return Matching CSENode. CSENode contains the actual value of the symbol.
     */
    public CSENode lookUp(String symbol){
        Environment env = currentEnv;
        boolean found = false;
        CSENode val = null;
        while(!found && env != null){
            found = env.checkForExistence(symbol);
            if(found)
                val = env.getValue(symbol);
            env = env.getParentEnviroment();
        }
        return val;
    }


    /***
     * Add a generated node during the evaluation of a node to the control. Added to the end.
     * @param node Node to be added
     */
    public void addToControl(CSENode node){
        this.control.add(node);
    }

    /***
     * Extend the controls with the set of nodes given as the parameter. Added to the end of the control.
     * @param nodes nodes to be added
     */
    public void addToControl(ArrayList<CSENode> nodes){
        this.control.addAll(nodes);
    }

    /***
     * Add the selected control structures of the conditional nodes to the control
     * @param nodes
     */
    public void addToControlStructure(ArrayList<ArrayList<CSENode>> nodes){
        this.controlStructures.addAll(nodes);
    }

    public void setStack(ArrayList<CSENode> stack) {
        this.stack = stack;
    }

    public void setCurrentEnv(Environment currentEnv) {
        this.currentEnv = currentEnv;
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
}
