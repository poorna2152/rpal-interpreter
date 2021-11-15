package cse.node;

import cse.CSEMachine;
import cse.Environment;
import definitions.OperationHandler;

import java.util.ArrayList;

public class GammaNode implements CSENode {
    @Override
    public String toString() {
        return "GammaNode";
    }

    /***
     * Evaluate based on the next node in  the stack
     * Top most in stack is a LambdaNode:
     *  Add an environmentNode to Stack with the environment set on the Lambda.
     *  If the node next to Lambda in stack is not a TupleNode add the node to value of LambdaNodes boundVariable
     *  If the node next to Lambda is a TupleNode check if bounding varibles in Lambda is 1 if so then add tuple as the value of bounding varible
     *  If Lambda has more than 1 bounding variables match them to elements in tuple
     *
     * Top most in stack YStarNode: If the second node is a LambdaNode remove Lambda and Ystar and add a EtaNode
     * If Tuple Node: Get the element in the tuple in the index given in the second element in stack
     * If EtaNode: Add to control two GammaNodes( to one which was popped). Add a LambdaNode to stack buu using the same parameters of EtaNode
     * If IdentifierNode: Use the operationHandler to handle the operation.
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {

        if(cseMachine.getStack().get(0) instanceof LambdaNode){
            LambdaNode lambda = (LambdaNode) cseMachine.getStack().remove(0);
            Environment newEnv = new Environment(lambda.getEnv());

            if(cseMachine.getStack().get(0) instanceof TupleNode){
                TupleNode tuple = (TupleNode) cseMachine.getStack().remove(0);
                if(lambda.getBoundVariable().size() ==1){
                    newEnv.addName(lambda.getBoundVariable().get(0),tuple);
                }
                else if(tuple.getChildren().size() != lambda.getBoundVariable().size()){
                    newEnv.addName(lambda.getBoundVariable().get(0),tuple);
                    for (int i = 1; i < lambda.getBoundVariable().size(); i++) {
                        CSENode rand = cseMachine.getStack().remove(0);
                        newEnv.addName(lambda.getBoundVariable().get(i), rand);
                    }
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


            EnviromentNode envNode = new EnviromentNode(newEnv);

            cseMachine.getStack().add(0,envNode);
            cseMachine.setCurrentEnv(newEnv);
            ArrayList<CSENode> nextControlStructure = cseMachine.getControlStructures().get(lambda.getIndex());
            cseMachine.addToControl(envNode);
            cseMachine.addToControl(nextControlStructure);

        }

        else if(cseMachine.getStack().get(0) instanceof YStarNode){
            if(cseMachine.getStack().get(1) instanceof LambdaNode){
                cseMachine.getStack().remove(0);
                LambdaNode lambda = (LambdaNode)cseMachine.getStack().remove(0);
                cseMachine.getStack().add(0,new EtaNode(lambda.getIndex(),lambda.getBoundVariable(), lambda.getEnv()));
            }
        }

        else if(cseMachine.getStack().get(0) instanceof TupleNode){
            TupleNode node = (TupleNode) cseMachine.getStack().remove(0);
            IntegerNode indexNode = (IntegerNode)cseMachine.getStack().remove(0);
            cseMachine.getStack().add(0,node.getChildren().get(indexNode.getValue()-1));
        }
        else if(cseMachine.getStack().get(0) instanceof EtaNode){
                EtaNode etaNode = (EtaNode)cseMachine.getStack().get(0);
                cseMachine.addToControl(new GammaNode());
                cseMachine.addToControl(new GammaNode());
                cseMachine.getStack().add(0,new LambdaNode(etaNode.getIndex(),etaNode.getBoundVariable(), etaNode.getEnv()));
        }

        else if (cseMachine.getStack().get(0) instanceof IdentifierNode){
            IdentifierNode node = (IdentifierNode)cseMachine.getStack().get(0);
            OperationHandler.getInstance().operateDefined(node.getLabel(),cseMachine);
        }

    }

}
