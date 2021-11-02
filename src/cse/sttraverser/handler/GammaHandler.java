package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.node.GammaNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class GammaHandler extends Handler {
    @Override
    public void handle(STNode node, PreOrderTraverser traverser, ArrayList<STNode> stack, int index) {
        if(node.getLabel().equals("gamma")){
            stack.addAll(0,node.getChildren());
            traverser.addToControl(index,new GammaNode());
            return ;
        }
        else{
            super.handle(node, traverser,stack,index);
        }
    }
}
