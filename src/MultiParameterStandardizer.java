import java.util.ArrayList;
import java.util.Arrays;

public class MultiParameterStandardizer implements  Standardizer {

    //              ++n -> lambda
    //      V           .E
    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();

        if(children.size() > 2){

            ArrayList<STNode> subStructure = new ArrayList<>();

            STNode E = children.get(children.size() - 1);
            children.remove(children.size() - 1);


            int count = 0;
            while (count < children.size()) {
                STNode lambda = new STNode("lambda");
                if (subStructure.size() != 0) {
                    STNode lastInserted = subStructure.get(subStructure.size() - 1);
                    lastInserted.setChildren(new ArrayList<>(Arrays.asList(children.get(count - 1), lambda)));
                    if (count == children.size()) {
                        lambda.setChildren(new ArrayList<>(Arrays.asList(children.get(count), E)));
                    }
                }
                subStructure.add(lambda);
                count++;
            }
            subStructure.get(0).setAstParentIndex(n.getAstParentIndex());
            return subStructure.get(0);
        }
        else{
            return n;
        }


    }

}
