package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiParameterStandardizer extends  Standardizer {

    /**
     *         ++n ->lambda
     *      V           .E
     * @param n
     * @param standardizedTree
     */

    @Override
    public void standardize(STNode n, StandardizedTree standardizedTree) {
        if(n.getLabel().equals("lambda")){
            ArrayList<STNode> children = n.getChildren();

            if(children.size() > 2){
                ArrayList<STNode> subStructure = new ArrayList<>();
                STNode E = children.remove(children.size() - 1);

                int count = 0;
                //generate the repetition structure
                while (count < children.size()) {
                    STNode lambda = new STNode("lambda");
                    if (subStructure.size() != 0) {
                        STNode lastInserted = subStructure.get(subStructure.size() - 1);
                        lastInserted.setChildren(new ArrayList<>(Arrays.asList(children.get(count - 1), lambda)));
                        //if last then add E to children
                        if (count == children.size()-1) {
                            lambda.setChildren(new ArrayList<>(Arrays.asList(children.get(count), E)));
                        }
                    }
                    subStructure.add(lambda);
                    count++;
                }
                subStructure.get(0).setAstParentIndex(n.getAstParentIndex());
                standardizedTree.setUpdatedNode(subStructure.get(0));
            }
            else{
                standardizedTree.setUpdatedNode(n);
            }
        }
        else{
            super.standardize(n,standardizedTree);
        }
    }

}
