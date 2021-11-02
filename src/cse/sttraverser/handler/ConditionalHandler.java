package cse.sttraverser.handler;

import cse.node.CSENode;
import cse.node.ConditionalNode;
import cse.sttraverser.PreOrderTraverser;
import standardize.STNode;

import java.util.ArrayList;

public class ConditionalHandler extends Handler {
    @Override
    public void handle(STNode node, PreOrderTraverser traverser,ArrayList<STNode> stack,int index) {

        if(node.getLabel().equals("->")){

            ConditionalNode conditionalNode = new ConditionalNode();

            int copyNextIndex = traverser.getNextIndex();

            int nextIndex = copyNextIndex;

            traverser.getControls().add(new ArrayList<>());
            traverser.setNextIndex(nextIndex+1);;
            traverser.traverse(node.getChildren().get(1),nextIndex);

            ArrayList<ArrayList<CSENode>> thenControls = new ArrayList<>();
            int traverserLen = traverser.getControls().size();
            for (int i = nextIndex+1; i < traverserLen ; i++) {
                thenControls.add(traverser.getControls().remove(nextIndex));
            }


            nextIndex = copyNextIndex;
            traverser.setNextIndex(nextIndex+1);;

            traverser.getControls().add(new ArrayList<>());

            traverser.traverse(node.getChildren().get(2),nextIndex);
            ArrayList<ArrayList<CSENode>> elseControls = new ArrayList<>();
            traverserLen = traverser.getControls().size();
            for (int i = nextIndex+1; i < traverserLen ; i++) {
                elseControls.add(traverser.getControls().remove(nextIndex));
            }

            conditionalNode.setThenControls(thenControls);
            conditionalNode.setElseControls(elseControls);

            traverser.addToControl(index,conditionalNode);
            stack.add(0,node.getChildren().get(0));
        }
        else{
            super.handle(node, traverser, stack, index);
        }
    }
}
