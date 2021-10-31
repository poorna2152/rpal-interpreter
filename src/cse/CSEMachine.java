package cse;

import standardize.STNode;

import java.util.ArrayList;

public class CSEMachine {
    private Environment currentEnv;
    private ArrayList<ArrayList<CSENode>> controlStructures;
    private ArrayList<ArrayList<CSENode>> control;
    private ArrayList<String> stack;

    public CSEMachine(STNode root) {
        PreOrderTraverser preOrderTraverser = new PreOrderTraverser(root);
        this.controlStructures = preOrderTraverser.startTraversal();
        this.currentEnv = new Environment(null);
        this.control.add();

    }

    public void evaluate(){
        CSENode poppedValue = null;
//        for (int i = 0; i < control.size(); i++) {
//            poppedValue = control.get(control.size()-1);
//            updateStack(poppedValue);
//        }
    }

//    public void updateStack(CSENode val){
//        switch (val){
//            case "gamma":
//                System.out.println("Gamma found");
//                break;
//
//            case "lambda":
//                System.out.println("Lambda found");
//                stack.add()
//                break;
//            default:
//                String value = lookUp(val);
//                if(value!=null){
//                    stack.add(0,value);
//                }
//
//        }
//    }

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
