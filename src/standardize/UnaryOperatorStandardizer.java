package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class UnaryOperatorStandardizer implements  Standardizer{

    //              gamma
    //      n-> operator           child[0]
    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();
        STNode operator = new STNode(n.getLabel());
        n.setLabel("gamma");
        n.setChildren(new ArrayList<>(Arrays.asList(operator,children.get(0))));

        return n;
    }

}
