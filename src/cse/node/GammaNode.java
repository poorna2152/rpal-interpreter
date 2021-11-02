package cse.node;

import cse.CSEMachine;
import cse.Environment;
import definitions.OperationHandler;
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

            if(cseMachine.getStack().get(0) instanceof TupleNode){
                TupleNode tuple = (TupleNode) cseMachine.getStack().remove(0);
                if(lambda.getBoundVariable().size() ==1){
                    newEnv.addName(lambda.getBoundVariable().get(0),tuple);
                }
               else{
                    ArrayList<CSENode> children = tuple.getChildren();
                    for (int i = 0; i < lambda.getBoundVariable().size(); i++) {
                        newEnv.addName(lambda.getBoundVariable().get(i), children.get(i));
                    }
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

        //checked
        else if(cseMachine.getStack().get(0) instanceof YStarNode){
            if(cseMachine.getStack().get(1) instanceof LambdaNode){
                cseMachine.getStack().remove(0);
                LambdaNode lambda = (LambdaNode)cseMachine.getStack().remove(0);
                cseMachine.getStack().add(0,new EtaNode(lambda.getIndex(),lambda.getBoundVariable(), lambda.getEnv()));
            }
        }

        //
        else if(cseMachine.getStack().get(0) instanceof TupleNode){
            TupleNode node = (TupleNode) cseMachine.getStack().remove(0);
            IntegerNode indexNode = (IntegerNode)cseMachine.getStack().remove(0);
            cseMachine.getStack().add(node.getChildren().get(indexNode.getValue()-1));
        }
        //checked
        else if(cseMachine.getStack().get(0) instanceof EtaNode){
                EtaNode etaNode = (EtaNode)cseMachine.getStack().get(0);
                cseMachine.addToControl(new GammaNode());
                cseMachine.addToControl(new GammaNode());
                cseMachine.getStack().add(0,new LambdaNode(etaNode.getIndex(),etaNode.getBoundVariable(), etaNode.getEnv()));
        }

        //checked -double check again
        else if (cseMachine.getStack().get(0) instanceof IdentifierNode){
            IdentifierNode node = (IdentifierNode)cseMachine.getStack().get(0);
            OperationHandler.getInstance().operateDefined(node.getLabel(),cseMachine);
        }

        else{
            System.out.println("Invalid node");
            System.out.println(cseMachine.getStack().get(0));
        }
    }

}
