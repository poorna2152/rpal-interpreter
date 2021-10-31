package standardize;

import java.util.ArrayList;
import java.util.Arrays;

public class TauStandardizer implements  Standardizer{

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
                start.setAstParentIndex(n.getAstParentIndex());
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
//        print_cond(start);
        return start;
    }
        public void print_cond(STNode node){
            System.out.println("tau printing");
            ArrayList<STNode> children = new ArrayList<>(Arrays.asList(node));
            while(children.size() > 0){
                STNode current = children.get(0);
                children.remove(0);
                System.out.println(current);
                System.out.println(current.getChildren());
                children.addAll(current.getChildren());
            }
            System.out.println("tau over");
        }

}
