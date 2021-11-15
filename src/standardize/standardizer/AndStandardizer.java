package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

import java.util.ArrayList;
import java.util.Arrays;

public class AndStandardizer extends   Standardizer{

    /**
     *                       =
     *               ,                       tau
     *     ++X->n->child[*][0]       ++E => n->child[*][1]
     * @param n
     * @param standardizedTree
     */


    @Override
    public void standardize(STNode n, StandardizedTree standardizedTree) {
        if(n.getLabel().equals("and")){
            ArrayList<STNode> children = n.getChildren();
            STNode comma = new STNode(",");
            STNode tau = new STNode("tau");
            STNode start = n;
            comma.setRevisit(true);

            for (int i = 0; i < children.size(); i++) {
                STNode currentNode = children.get(i);
                comma.addChild(currentNode.getChildren().get(0));
                tau.addChild(currentNode.getChildren().get(1));
            }

            start.setLabel("=");
            start.setChildren(new ArrayList<>(Arrays.asList(comma,tau)));
            start.setRevisit(true);
            standardizedTree.setUpdatedNode(n);
        }
        else{
            super.standardize(n,standardizedTree);
        }
    }


}
