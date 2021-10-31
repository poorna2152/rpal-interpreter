import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.CheckedInputStream;

public class AtStandardizer implements  Standardizer{


    //                                      gamma1
    //                          gamma2                                           E2 = n->child[2]
    //      N =  n->child[1]                      E1=  n->child[0]


    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();

        n.setLabel("gamma");
        STNode gamma2 = new STNode("gamma");
        STNode E1 = children.get(0);
        STNode N = children.get(1);
        STNode E2 = children.get(2);

        gamma2.setChildren(new ArrayList<>(Arrays.asList(N,E1)));
        n.setChildren(new ArrayList<>(Arrays.asList(gamma2,E2)));

        return n;
    }


}

