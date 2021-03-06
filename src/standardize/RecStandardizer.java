package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class RecStandardizer implements  Standardizer{

    //                                      =
    //      X->n->child[0]->child[0]                                        gamma
    //                                          Ystar                                lambda
    //                                                 X->n->child[0]->child[0]               E->n->child[0]->child[1]
    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren().get(0).getChildren();

        STNode X = children.get(0);
        STNode E = children.get(1);
        STNode lambda = new STNode("lambda");
        STNode gamma = new STNode("gamma");
        STNode Ystar = new STNode("Ystar");


        lambda.setChildren(new ArrayList<>(Arrays.asList(X,E)));
        gamma.setChildren(new ArrayList<>(Arrays.asList(Ystar,lambda)));

        n.setLabel("=");
        n.setChildren(new ArrayList<>(Arrays.asList(X,gamma)));

        return n;
    }

}
