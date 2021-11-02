package cse.sttraverser.handler;

import cse.node.YStarNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class YStarHandler extends Handler {
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
         if(node.getLabel().equals("Ystar")){
            traverser.addToControl(index, new YStarNode());
         }
        else{
            super.handle(node, traverser, stack, index);
        }
    }
}