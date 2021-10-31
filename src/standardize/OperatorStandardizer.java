package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class OperatorStandardizer implements  Standardizer{

    //              n -> gamma
    //      gamma2           child[1]
    //operator  child[0]
    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();

        STNode operator = new STNode(n.getLabel());
        STNode gamma2 = new STNode("gamma");

        gamma2.setChildren(new ArrayList<>(Arrays.asList(operator,children.get(0))));

        n.setLabel("gamma");
        n.setChildren(new ArrayList<>(Arrays.asList(gamma2,children.get(1))));

        return n;
    }

}
