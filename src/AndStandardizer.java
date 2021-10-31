import java.util.ArrayList;
import java.util.Arrays;

public class AndStandardizer implements  Standardizer{

    //              ++n -> gamma
    //      gamma2           child[0]
    //aug       .nil
    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();
        ArrayList<STNode> subStructure = new ArrayList<>();
        STNode start = n;

        int count = 1;
        while(count <= children.size()){
            STNode gamma1 = new STNode("gamma");
            STNode gamma2 = new STNode("gamma");
            gamma1.setChildren(new ArrayList<>(Arrays.asList(gamma2,children.get(children.size()-count))));
            if(subStructure.size() == 0){
                start = gamma1;
            }
            else{
                STNode lastInserted = subStructure.get(subStructure.size()-1);
                lastInserted.setChildren(new ArrayList<>(Arrays.asList(new STNode("aug"),gamma1)));
                if(count == children.size()){
                    gamma2.setChildren(new ArrayList<>(Arrays.asList(new STNode("aug"),new STNode("nil"))));
                }
            }
            subStructure.add(gamma2);
            count++;
        }

        return start;
    }


}
