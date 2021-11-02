package cse.node;

import cse.CSEMachine;
import cse.Environment;
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
            if(cseMachine.getStack().get(0) instanceof TauNode){
                TauNode tauNode = (TauNode) cseMachine.getStack().remove(0);
                ArrayList<CSENode> children = tauNode.getChildren();
                for (int i = 0; i < lambda.getBoundVariable().size(); i++) {
                    newEnv.addName(lambda.getBoundVariable().get(i), children.get(i));
                }
            }
            else {
                for (int i = 0; i < lambda.getBoundVariable().size(); i++) {
                    CSENode rand = cseMachine.getStack().remove(0);
                    newEnv.addName(lambda.getBoundVariable().get(i), rand);
                }
            }
            cseMachine.setCurrentEnv(newEnv);


            EnviromentNode envNode = new EnviromentNode(newEnv);


            cseMachine.getStack().add(0,envNode);

            ArrayList<CSENode> nextControlStructure = cseMachine.getControlStructures().get(lambda.getIndex());
            cseMachine.addToControl(envNode);
            cseMachine.addToControl(nextControlStructure);

        }
        else if(cseMachine.getStack().get(0) instanceof YStarNode){
            System.out.println("In Ystar gamma");
            if(cseMachine.getStack().get(1) instanceof LambdaNode){

                cseMachine.getStack().remove(0);
                LambdaNode lambda = (LambdaNode)cseMachine.getStack().remove(0);
                cseMachine.getStack().add(0,new EtaNode(lambda.getIndex(),lambda.getBoundVariable(), lambda.getEnv()));
            }
        }
        else if(cseMachine.getStack().get(0) instanceof TauNode){
            TauNode node = (TauNode)cseMachine.getStack().remove(0);
            SymbolNode indexNode = (SymbolNode)cseMachine.getStack().remove(0);
            int index = Integer.parseInt(indexNode.getVal(indexNode.getLabel()));
            cseMachine.getStack().add(node.getChildren().get(index-1));
        }
        else if(cseMachine.getStack().get(0) instanceof EtaNode){
                EtaNode etaNode = (EtaNode)cseMachine.getStack().get(0);
                cseMachine.addToControl(new GammaNode());
                cseMachine.addToControl(new GammaNode());
                cseMachine.getStack().add(0,new LambdaNode(etaNode.getIndex(),etaNode.getBoundVariable(), etaNode.getEnv()));
        }
        else{
            SymbolNode rator = (SymbolNode)cseMachine.getStack().remove(0);
            String ratorLabel = rator.getLabel();
            switch (ratorLabel){
                case "<ID:Isinteger>":
                case "<ID:Istruthvalue>":
                case "<ID:Isstring>":
                case "<ID:Istuple>":
                case "<ID:Isfunction>":
                    SymbolNode rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                    String rand1Label = this.getType(rand1.getLabel());
                    TypeIdentificationOperators typeOp = new TypeIdentificationOperators();
                    String result = typeOp.operate(this.getVal(ratorLabel),rand1Label);
                    cseMachine.getStack().add(0,new SymbolNode(result));
                    break;
                case "<ID:Print>":
                    CSENode node1 = cseMachine.getStack().remove(0);
                    cseMachine.getStack().add(0,node1);
                    System.out.println(node1);
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
