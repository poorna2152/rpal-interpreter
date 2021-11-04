package cse.sttraverser;

import cse.node.*;
import cse.sttraverser.handler.Handler;
import standardize.STNode;

import java.util.ArrayList;
import java.util.Arrays;

public class PreOrderTraverser {
    private ArrayList<ArrayList<CSENode>> controls;
    private STNode root;
    private int nextIndex = 0;
    private Handler handlers;

    public PreOrderTraverser(STNode node) {
        this.root = node;
        this.handlers = new HandlerBuilder().buildTraverserHandler();
        this.controls = new ArrayList<>();
        this.controls.add(new ArrayList<>());
    }

    public ArrayList<ArrayList<CSENode>> startTraversal(){
        this.traverse(root,0);
//        for (int i = 0; i < controls.size(); i++) {
//            System.out.println(controls.get(i));
//        }
        return controls;

    }

    public ArrayList<ArrayList<CSENode>> getControls() {
        return controls;
    }


    public void addToControl(int index,CSENode node){
        this.controls.get(index).add(node);
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void traverse(STNode node,int index){
        ArrayList<STNode> stack = new ArrayList<>(Arrays.asList(node));
        STNode currentNode = null;

        while(stack.size() > 0){
            currentNode = stack.remove(0);
            this.handlers.handle(currentNode,this,stack,index);
        }

    }
}
