package cse;

import java.util.ArrayList;

public class GammaNode implements CSENode {

    @Override
    public void evaluate(CSEMachine cseMachine) {
        if(cseMachine.getStack().get(0) instanceof LambdaNode){
            LambdaNode lambda = (LambdaNode) cseMachine.getStack().get(0);
            SymbolNode rand = (SymbolNode)cseMachine.getStack().get(1);

            Environment newEnv = new Environment(cseMachine.getCurrentEnv());
            newEnv.addName(lambda.getBoundVariable(),rand.getLabel());

            cseMachine.setCurrentEnv(newEnv);

            //remove lambda and rand
            cseMachine.getStack().remove(0);
            cseMachine.getStack().remove(0);

            EnviromentNode envNode = new EnviromentNode(newEnv);


            cseMachine.getStack().add(envNode);

            ArrayList<CSENode> nextControlStructure = cseMachine.getControlStructures().get(lambda.getIndex());
            cseMachine.addToControl(envNode);
            cseMachine.addToControl(nextControlStructure);

        }
        else{
            SymbolNode rator = (SymbolNode)cseMachine.getStack().get(0);
            SymbolNode rand = (SymbolNode)cseMachine.getStack().get(0);
            String label = rand.getLabel()+rator.getLabel();
            cseMachine.getStack().add(new SymbolNode(label));
        }
    }
}
