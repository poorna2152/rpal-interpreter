package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

public abstract class Handler {
    private String type;
    private Handler nextHandler;

    public void handle(STNode node, PreOrderTraverser traverser, int index){
        this.nextHandler.handle(node,traverser,index);
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
