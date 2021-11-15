package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

import java.util.ArrayList;
import java.util.Arrays;

public class WithinStandardizer extends Standardizer{

    /***
     * Generate the following structure
     *                                             =
     *         X2-= n->child[1]->child[0]                                           gamma
     *                                                                 lambda                          E1=  n->child[0]->child[1]
     *                                        X1 = n->child[0]->child[0]      E2=  n->child[1]->child[1]
     * @param n
     * @param standardizedTree
     */

    @Override
    public void standardize(STNode n, StandardizedTree standardizedTree) {
        if(n.getLabel().equals("within")){
            ArrayList<STNode> withinChildren = n.getChildren();
            ArrayList<STNode> equalLeftChildren = withinChildren.get(0).getChildren();
            ArrayList<STNode> equalRightChildren = withinChildren.get(1).getChildren();

            n.setLabel("=");
            STNode lambda = new STNode("lambda");
            STNode gamma = new STNode("gamma");
            STNode X1 = equalLeftChildren.get(0);
            STNode X2 = equalRightChildren.get(0);
            STNode E1 = equalLeftChildren.get(1);
            STNode E2 = equalRightChildren.get(1);

            lambda.setChildren(new ArrayList<>(Arrays.asList(X1,E2)));
            gamma.setChildren(new ArrayList<>(Arrays.asList(lambda,E1)));
            n.setChildren(new ArrayList<>(Arrays.asList(X2,gamma)));


            standardizedTree.setUpdatedNode(n);
        }
        else{
            super.standardize(n,standardizedTree);
        }

    }


}

