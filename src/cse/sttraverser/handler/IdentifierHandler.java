package cse.sttraverser.handler;

import cse.node.IdentifierNode;
import cse.node.TauNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class IdentifierHandler extends Handler{
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().indexOf("<ID:") == 0){
            String label = node.getLabel();
            int startIndex = label.indexOf(":");
            traverser.addToControl(index,new IdentifierNode(label.substring(startIndex+1,label.length()-1)));
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
