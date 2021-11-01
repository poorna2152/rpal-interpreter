package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class AndStandardizer implements  Standardizer{

    //                      =
    //          ,                       tau
    //++X->n->child[*][0]       ++E => n->child[*][1]

    @Override
    public STNode standardize(STNode n) {

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
        return start;
    }


}
