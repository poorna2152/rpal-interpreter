package cse.sttraverser.handler;

import cse.node.TauNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class TauHandler extends Handler {
    /***
     * Add a TauNode to current control structure
     * @param node
     * @param traverser
     * @param stack
     * @param index
     */
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().equals("tau")){
            traverser.addToControl(index,new TauNode(node.getChildren().size()));
            stack.addAll(0,node.getChildren());
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
