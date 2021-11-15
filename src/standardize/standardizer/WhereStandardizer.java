package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

import java.util.ArrayList;
import java.util.Arrays;

public class WhereStandardizer extends Standardizer{

    /***
     *     Generate the following structure
     *                          n -> gamma
     *                  lambda                  E = n->child[1]->child[1]
     *     X = n->child[1]->child[0]     P = n->child[0]
     * @param n
     * @param standardizedTree
     */


    @Override
    public void standardize(STNode n, StandardizedTree standardizedTree) {
        if(n.getLabel().equals("where")){
            ArrayList<STNode> letChildren = n.getChildren();
            ArrayList<STNode>  equalChildren = letChildren.get(1).getChildren();

            STNode lambda = new STNode("lambda");
            STNode P = letChildren.get(0);
            STNode E = equalChildren.get(1);
            STNode X = equalChildren.get(0);

            n.setLabel("gamma");
            lambda.setChildren(new ArrayList<>(Arrays.asList(X,P)));
            n.setChildren(new ArrayList<>(Arrays.asList(lambda,E)));


            standardizedTree.setUpdatedNode(n);
        }
        else{
            super.standardize(n,standardizedTree);
        }

    }

}
