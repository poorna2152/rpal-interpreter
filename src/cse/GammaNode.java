package cse;

import definitions.TypeIdentificationOperators;

import java.util.ArrayList;

public class GammaNode implements CSENode {
    @Override
    public String toString() {
        return "GammaNode";
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        if(cseMachine.getStack().get(0) instanceof LambdaNode){
            LambdaNode lambda = (LambdaNode) cseMachine.getStack().remove(0);
            Environment newEnv = new Environment(cseMachine.getCurrentEnv());

            for (int i = 0; i < lambda.getBoundVariable().size(); i++) {
                CSENode rand = cseMachine.getStack().remove(0);
                newEnv.addName(lambda.getBoundVariable().get(i),rand);
            }

            cseMachine.setCurrentEnv(newEnv);


            EnviromentNode envNode = new EnviromentNode(newEnv);


            cseMachine.getStack().add(0,envNode);

            ArrayList<CSENode> nextControlStructure = cseMachine.getControlStructures().get(lambda.getIndex());
            cseMachine.addToControl(envNode);
            cseMachine.addToControl(nextControlStructure);

        }
        else{
            SymbolNode rator = (SymbolNode)cseMachine.getStack().remove(0);
            SymbolNode rand1 = (SymbolNode)cseMachine.getStack().remove(0);
            String ratorLabel = rator.getLabel();
            String rand1Label = this.getType(rand1.getLabel());
            switch (ratorLabel){
                case "<ID:Isinteger>":
                case "<ID:Istruthvalue>":
                case "<ID:Isstring>":
                case "<ID:Istuple>":
                case "<ID:Isfunction>":
                    TypeIdentificationOperators typeOp = new TypeIdentificationOperators();
                    String result = typeOp.operate(this.getVal(ratorLabel),rand1Label);
                    cseMachine.getStack().add(0,new SymbolNode(result));
                    break;
                case "<ID:Print>":
                    cseMachine.getStack().add(0,rand1);
                    System.out.println(ratorLabel);
                default:
                    System.out.println("ID not found");
            }

        }
    }

    public String getVal(String label){
        int index = label.indexOf(":");
        return label.substring(index+1,label.length()-1);
    }
    public String getType(String label){
        System.out.println("get type");
        System.out.println(label);
        int index = label.indexOf(":");
        return label.substring(1,index);
    }
}
