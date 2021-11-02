package cse.sttraverser.handler;

import cse.node.IdentifierNode;
import cse.node.LambdaNode;
import cse.node.YStarNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class LambdaHandler extends Handler{
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack,int index) {

        if(node.getLabel().equals("lambda")){
            ArrayList<String> boundVariable = new ArrayList<>();

            if(node.getChildren().get(0).getLabel().equals(",")){
                ArrayList<STNode> children = node.getChildren().get(0).getChildren();
                for (int i = 0; i < children.size(); i++) {
                    String label = children.get(i).getLabel();
                    int startIndex = label.indexOf(":");
                    boundVariable.add(label.substring(startIndex+1,label.length()-1));
                }
            }

            else{
                String label = node.getChildren().get(0).getLabel();
                int startIndex = label.indexOf(":");
                boundVariable.add(label.substring(startIndex+1,label.length()-1));
            }

            traverser.setNextIndex(traverser.getNextIndex()+1);
            traverser.addToControl(index,new LambdaNode(traverser.getNextIndex(), boundVariable));
            traverser.getControls().add(new ArrayList<>());
            traverser.traverse(node.getChildren().get(1),traverser.getNextIndex());
        }
        else{
            super.handle(node, traverser, stack,index);
        }
    }
}
