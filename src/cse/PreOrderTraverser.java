package cse;

import standardize.STNode;

import java.util.ArrayList;
import java.util.Arrays;

public class PreOrderTraverser {
    private ArrayList<ArrayList<CSENode>> controls;
    private STNode root;
    private int nextIndex = 0;

    public PreOrderTraverser(STNode node) {
        this.root = node;
        this.controls = new ArrayList<>();
        this.controls.add(new ArrayList<>());
    }

    public ArrayList<ArrayList<CSENode>> startTraversal(){
        this.traverse(root,0);
        for (int i = 0; i < controls.size(); i++) {
            System.out.println(controls.get(i));
        }

        return controls;

    }

    public void traverse(STNode node,int index){
        ArrayList<STNode> stack = new ArrayList<>(Arrays.asList(node));
        STNode currentNode = null;

        while(stack.size() > 0){
            currentNode = stack.get(0);
            stack.remove(0);

            if(currentNode.getLabel()=="lambda"){
                ArrayList<String> boundVariable = new ArrayList<>();
                if(currentNode.getChildren().get(0).getLabel() ==","){
                    ArrayList<STNode> children = currentNode.getChildren().get(0).getChildren();
                    for (int i = 0; i < children.size(); i++) {
                        boundVariable.add(children.get(i).getLabel());
                    }
                }
                else{
                    boundVariable.add(currentNode.getChildren().get(0).getLabel());
                }
                nextIndex++;
                controls.get(index).add(new LambdaNode(nextIndex, boundVariable));
                controls.add(new ArrayList<>());
                traverse(currentNode.getChildren().get(1),nextIndex);

            }
            else if(currentNode.getLabel()== "->"){
                ConditionalNode conditionalNode = new ConditionalNode();
                controls.add(new ArrayList<>());
                controls.add(new ArrayList<>());

                traverse(currentNode.getChildren().get(1),index+1);
                traverse(currentNode.getChildren().get(2),index+2);

                conditionalNode.setThenControls(controls.remove(index+1));
                conditionalNode.setElseControls(controls.remove(index+2));

                controls.get(index).add(new SymbolNode("beta"));
                controls.get(index).add(conditionalNode);
            }
            else if(currentNode.getLabel()== "tau"){
                controls.get(index).add(new TauNode(currentNode.getChildren().size()));
                stack.addAll(0,currentNode.getChildren());
            }
            else if(currentNode.getLabel()=="gamma"){
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
