package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.node.GammaNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

public class GammaHandler extends Handler {
    @Override
    public void handle(STNode node, PreOrderTraverser traverser) {
        if(node.getLabel() =="gamma"){
            traverser.stack.addAll(0,currentNode.getChildren());
            controls.get(index).add(new GammaNode());
            return ;
        }
        super.handle(node);
    }
}
