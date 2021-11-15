package cse.sttraverser.handler;

import cse.node.DummyNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class DummyHandler extends Handler {
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().equals("<dummy>")) {
            traverser.addToControl(index,new DummyNode());
        }
        else{super.handle(node, traverser, stack, index);}
    }
}
