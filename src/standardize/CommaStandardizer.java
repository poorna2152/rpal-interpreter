package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class CommaStandardizer implements  Standardizer{

    //                      lambda1
    //          Temp                       gamma1
    //                          lambda2             ++gamma2
    //                       X.i       .E   Temp    <Integer.i>

    @Override
    public STNode standardize(STNode n) {

        ArrayList<STNode> children = n.getChildren();
        ArrayList<STNode> commaChildren = children.get(0).getChildren();

        ArrayList<STNode> subStructure = new ArrayList<>();
        STNode E = children.get(1);
        STNode Temp = new STNode("Temp");
        STNode start = new STNode("gamma");

        int count = commaChildren.size();
        while(count > 0){
            STNode gamma1 = new STNode("gamma");
            STNode lambda1 = new STNode("lambda");
            STNode gamma2 = new STNode("gamma");
            STNode I = new STNode(String.valueOf(count));

            gamma2.setChildren(new ArrayList<>(Arrays.asList(Temp,I)));
            gamma1.setChildren(new ArrayList<>(Arrays.asList(lambda1,gamma2)));

            if(subStructure.size() == 0){
                start = gamma1;
            }
            else{
                STNode lastInserted = subStructure.get(subStructure.size()-1);
                STNode X_i = new STNode(commaChildren.get(count).getLabel());
                lastInserted.setChildren(new ArrayList<>(Arrays.asList(X_i,gamma1)));
                if(count == 1){
                    STNode X_i_current = new STNode(commaChildren.get(count-1).getLabel());
                    lambda1.setChildren(new ArrayList<>(Arrays.asList(X_i_current,E)));
                }
            }

            count--;
        }
        n.setChildren(new ArrayList<>(Arrays.asList(Temp,start)));
        return n;
    }


}
