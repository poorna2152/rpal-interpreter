package cse.sttraverser.handler;

import cse.node.LambdaNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class LambdaHandler extends Handler{
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, int index) {
        if(node.getLabel().equals("lambda")){
            ArrayList<String> boundVariable = new ArrayList<>();

            if(node.getChildren().get(0).getLabel().equals(",")){
                ArrayList<STNode> children = node.getChildren().get(0).getChildren();
                for (int i = 0; i < children.size(); i++) {
                    boundVariable.add(children.get(i).getLabel());
                }
            }

            else{
                boundVariable.add(node.getChildren().get(0).getLabel());
            }

            traverser.setNextIndex(traverser.getNextIndex()+1);
            traverser.getControls().get().add(new LambdaNode(traverser.getNextIndex(), boundVariable));
            controls.add(new ArrayList<>());
            traverse(currentNode.getChildren().get(1),nextIndex);

        }
        super.handle(node, traverser);
    }
}
