package cse.sttraverser.handler;

import cse.node.BooleanNode;
import cse.node.IdentifierNode;
import cse.node.IntegerNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class BooleanHandler extends Handler {

    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().equals("<true>") || node.getLabel().equals("<false>")){
            String label = node.getLabel();
            String stringVal = label.substring(1,label.length()-1);
            boolean bool = Boolean.parseBoolean(stringVal);
            traverser.addToControl(index,new BooleanNode(bool));
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
