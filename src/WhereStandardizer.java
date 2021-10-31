import java.util.ArrayList;
import java.util.Arrays;

public class WhereStandardizer implements Standardizer{

    //                                  n -> gamma
    //                          lambda                  E = n->child[1]->child[1]
    //X = n->child[1]->child[0]     P = n->child[0]

    @Override
    public STNode standardize(STNode n) {
        ArrayList<STNode> letChildren = n.getChildren();
        ArrayList<STNode>  equalChildren = letChildren.get(1).getChildren();

        STNode lambda = new STNode("lambda");
        STNode P = letChildren.get(0);
        STNode E = equalChildren.get(1);
        STNode X = equalChildren.get(0);

        n.setLabel("gamma");
        lambda.setChildren(new ArrayList<>(Arrays.asList(X,P)));
        n.setChildren(new ArrayList<>(Arrays.asList(lambda,E)));


        return n;
    }

}
