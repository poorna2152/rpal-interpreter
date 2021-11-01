package cse;

import java.util.ArrayList;

public class GammaNode implements CSENode {
    @Override
    public String toString() {
        return "GammaNode";
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        if(cseMachine.getStack().get(0) instanceof LambdaNode){
            LambdaNode lambda = (LambdaNode) cseMachine.getStack().get(0);
            Environment newEnv = new Environment(cseMachine.getCurrentEnv());

            for (int i = 0; i < lambda.getBoundVariable().size(); i++) {
                CSENode rand = cseMachine.getStack().remove(1);
                newEnv.addName(lambda.getBoundVariable().get(i),rand);
            }

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
