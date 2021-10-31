import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.CheckedInputStream;

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
        while(count < children.size()){
            STNode lambda = new STNode("lambda");
            if (subStructure.size() != 0) {
                STNode lastInserted = subStructure.get(subStructure.size() - 1);
                lastInserted.setChildren(new ArrayList<>(Arrays.asList(new STNode(children.get(count - 1).getLabel()), lambda)));
                if (count == children.size() - 1) {
                    lambda.setChildren(new ArrayList<>(Arrays.asList(new STNode(children.get(count - 1).getLabel()), E)));
                }
            }
            subStructure.add(lambda);
            count++;
        }
        n.setChildren(new ArrayList<>(Arrays.asList(P,subStructure.get(0))));
        return n;
    }


}

