package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.node.ConditionalNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class ConditionalHandler extends Handler {

    /***
     * Add  new control structure for thenControls and elseControls using recursive calls to traverse function in traverser
     * Add a conditionalNode to control structure
     *
     * @param node
     * @param traverser
     * @param stack
     * @param index
     */
    @Override
    public void handle(STNode node, PreOrderTraverser traverser,ArrayList<STNode> stack,int index) {

        if(node.getLabel().equals("->")){

            ConditionalNode conditionalNode = new ConditionalNode();

            int copyNextIndex = traverser.getNextIndex();

            int nextIndex = copyNextIndex;

            //add new control to controlStructure
            traverser.getControls().add(new ArrayList<>());
            //set index to add to new control
            traverser.setNextIndex(nextIndex+1);;
            //traverse the control
            traverser.traverse(node.getChildren().get(1),nextIndex+1);
            ArrayList<ArrayList<CSENode>> thenControls = new ArrayList<>();
            thenControls.add(traverser.getControls().get(nextIndex+1));

            //return from previous thenTraversal and start traversing else controls
            nextIndex = traverser.getNextIndex();
            traverser.setNextIndex(nextIndex+1);
            traverser.getControls().add(new ArrayList<>());

            traverser.traverse(node.getChildren().get(2),nextIndex+1);
            ArrayList<ArrayList<CSENode>> elseControls = new ArrayList<>();
            elseControls.add(traverser.getControls().get(nextIndex+1));

            conditionalNode.setThenControls(thenControls);
            conditionalNode.setElseControls(elseControls);

            //after else and then traversals are over traverse the rest of the nodes
            traverser.addToControl(index,conditionalNode);
            stack.add(0,node.getChildren().get(0));
        }
        else{
            super.handle(node, traverser, stack, index);
        }
    }
}
