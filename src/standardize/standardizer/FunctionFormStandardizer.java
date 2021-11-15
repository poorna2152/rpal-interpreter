package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionFormStandardizer extends Standardizer{

    /**
     *                                       =
     *           P-= n->child[0]                                     lambda
     *                                   V=n->child[0<x<child.size()-2]      .E= n->child[last]
     * @param n
     * @param standardizedTree
     */


    public void standardize(STNode n, StandardizedTree standardizedTree) {
        if(n.getLabel().equals("function_form")){
            ArrayList<STNode> children = n.getChildren();
            ArrayList<STNode> subStructure = new ArrayList<>();

            n.setLabel("=");
            STNode P = new STNode(children.get(0).getLabel());
            STNode E = children.get(children.size()-1);
            int count = 1;
            //generate the repetition structure
            while(count <= children.size()-1){
                STNode lambda = new STNode("lambda");
                if (subStructure.size() != 0) {
                    STNode lastInserted = subStructure.get(subStructure.size() - 1);
                    lastInserted.setChildren(new ArrayList<>(Arrays.asList(children.get(count-1), lambda)));
                    if (count == children.size() - 1) {
                        lastInserted.setChildren(new ArrayList<>(Arrays.asList(children.get(count-1), E)));
                    }
                    else{
                        subStructure.add(lambda);
                    }
                }
                else{
                    subStructure.add(lambda);
                }
                count++;
            }

            n.setChildren(new ArrayList<>(Arrays.asList(P,subStructure.get(0))));
            standardizedTree.setUpdatedNode(n);
        }
        else{
            super.standardize(n,standardizedTree);
        }

    }


}

