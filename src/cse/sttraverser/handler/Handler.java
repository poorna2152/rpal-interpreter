package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.node.SymbolNode;
import cse.node.YStarNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public abstract class Handler {
    private Handler nextHandler = null;

    /***
     * Base handler implementation
     * if has a nextHandler pass to nextHandler
     * else add the node to control structure as a SymbolNode
     * @param node
     * @param traverser
     * @param stack
     * @param index
     */
    public void handle(STNode node, PreOrderTraverser traverser,  ArrayList<STNode> stack,int index){
        if(this.nextHandler !=null){
            this.nextHandler.handle(node,traverser,stack,index);
        }
        else{
            stack.addAll(0,node.getChildren());
            traverser.addToControl(index, new SymbolNode(node.getLabel()));
        }
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
