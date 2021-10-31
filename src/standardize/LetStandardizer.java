package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class LetStandardizer implements Standardizer{

    //                                  n -> gamma
    //                          lambda                  E = n->child[0]->child[1]
    //X = n->child[0]->child[0]     P = n->child[1]

    @Override
    public STNode standardize(STNode n) {
        ArrayList<STNode> letChildren = n.getChildren();
        ArrayList<STNode>   equalChildren = letChildren.get(0).getChildren();

        STNode lambda = new STNode("lambda");
        STNode P = letChildren.get(1);
        STNode E = equalChildren.get(1);
        STNode X = equalChildren.get(0);

        n.setLabel("gamma");
        lambda.setChildren(new ArrayList<>(Arrays.asList(X,P)));
        n.setChildren(new ArrayList<>(Arrays.asList(lambda,E)));

//        print_cond(n);
        return n;
    }

    public void print_cond(STNode node){
        System.out.println("let printing");
        ArrayList<STNode> children = new ArrayList<>(Arrays.asList(node));
        while(children.size() > 0){
            STNode current = children.get(0);
            children.remove(0);
            System.out.println(current);
            System.out.println(current.getChildren());
            children.addAll(current.getChildren());
        }
        System.out.println("let over");
    }

}
