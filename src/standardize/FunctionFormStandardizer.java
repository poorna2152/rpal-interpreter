package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class FunctionFormStandardizer implements  Standardizer{


    //                                      =
    //      P-= n->child[0]                                     lambda
    //                              V=n->child[0<x<child.size()-2]      .E= n->child[last]

    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();
        ArrayList<STNode> subStructure = new ArrayList<>();

        n.setLabel("=");
        STNode P = new STNode(children.get(0).getLabel());
        STNode E = children.get(children.size()-1);
        int count = 1;
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
//        print_cond(n);
        return n;
    }
    public void print_cond(STNode node){
        System.out.println("func printing");
        ArrayList<STNode> children = new ArrayList<>(Arrays.asList(node));
        while(children.size() > 0){
            STNode current = children.get(0);
            children.remove(0);
            System.out.println(current);
            System.out.println(current.getChildren());
            children.addAll(current.getChildren());
        }
        System.out.println("func over");
    }

}

