package cse.sttraverser.handler;

import cse.node.IdentifierNode;
import cse.node.IntegerNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class IntegerHandler extends Handler {

    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().indexOf("<INT:") == 0){
            String label = node.getLabel();
            int startIndex = label.indexOf(":");
            String stringVal = label.substring(startIndex+1,label.length()-1);
            traverser.addToControl(index,new IntegerNode(Integer.parseInt(stringVal)));
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
