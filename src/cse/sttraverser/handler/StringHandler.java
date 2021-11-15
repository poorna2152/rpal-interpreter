package cse.sttraverser.handler;

import cse.node.IntegerNode;
import cse.node.StringNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class StringHandler extends Handler {
    /***
     * Check done for the <STR: tag in the STNode.name
     * @param node
     * @param traverser
     * @param stack
     * @param index
     */
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().indexOf("<STR:") == 0) {
            String label = node.getLabel();
            int startIndex = label.indexOf(":");
            String stringVal = label.substring(startIndex+1,label.length()-1);
            traverser.addToControl(index,new StringNode(stringVal.substring(1,stringVal.length()-1)));
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
