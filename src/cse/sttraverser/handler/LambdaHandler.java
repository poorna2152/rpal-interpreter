package cse.sttraverser.handler;

import cse.node.IdentifierNode;
import cse.node.LambdaNode;
import cse.node.YStarNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class LambdaHandler extends Handler{
    /***
     * Add lambda Node to control structure.
     * If in ST LambdaNode has CommaNode as a child Add the children of the CommaNode to LamndaNode's bound variables
     * Else add the lambdaNode child as a bound variable to LambdaNode in control structure.
     * @param node
     * @param traverser
     * @param stack
     * @param index
     */
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
            traverser.getControls().add(new ArrayList<>());
            traverser.addToControl(index,new LambdaNode(traverser.getNextIndex(), boundVariable));
            traverser.traverse(node.getChildren().get(1),traverser.getNextIndex());

        }
        else{
            super.handle(node, traverser, stack,index);
        }
    }
}
