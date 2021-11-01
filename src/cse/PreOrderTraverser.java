package cse;

import standardize.STNode;

import java.util.ArrayList;
import java.util.Arrays;

public class PreOrderTraverser {
    private ArrayList<ArrayList<CSENode>> controls;
    private STNode root;

    public PreOrderTraverser(STNode node) {
        this.root = node;
        this.controls = new ArrayList<>();
        this.controls.add(new ArrayList<>());
    }

    public ArrayList<ArrayList<CSENode>> startTraversal(){
        this.traverse(root,0);
        System.out.println(controls);
        System.out.println(controls.size());

        return controls;

    }

    public void traverse(STNode node,int index){
        ArrayList<STNode> stack = new ArrayList<>(Arrays.asList(node));
        STNode currentNode = null;

        while(stack.size() > 0){
            currentNode = stack.get(0);
            stack.remove(0);

            if(currentNode.getLabel()=="lambda"){
                String boundVariable = currentNode.getChildren().get(0).getLabel();
                controls.get(index).add(new LambdaNode(controls.size()-1, boundVariable ));
                controls.add(new ArrayList<>());
                traverse(currentNode.getChildren().get(1),index+1);

            }else if(currentNode.getLabel()=="gamma"){
                stack.addAll(0,currentNode.getChildren());
                controls.get(index).add(new GammaNode());
            }
            else{
                stack.addAll(0,currentNode.getChildren());
                controls.get(index).add(new SymbolNode(currentNode.getLabel()));
            }

        }

    }
}
