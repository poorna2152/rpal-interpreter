import java.util.ArrayList;
import java.util.Arrays;

public class ConditionalStandardizer implements Standardizer{

    //                                          n -> gamma1
    //                              gamma2                                  nil
    //                      gamma3                                               lambda1
    //      gamma4                              lambda2                    ()      F-= n->child[2]
    //cond        B=n->child[0]          ()             T=n->child[1]

    @Override
    public STNode standardize(STNode n) {
        ArrayList<STNode> letChildren = n.getChildren();
        ArrayList<STNode>   equalChildren = letChildren.get(0).getChildren();

        STNode lambda1 = new STNode("lambda");
        STNode lambda2 = new STNode("lambda");

        STNode parenthesis = new STNode("()");
        STNode nil = new STNode("nil");

        STNode B = letChildren.get(0);
        STNode T = letChildren.get(1);
        STNode F = letChildren.get(2);
        STNode Cond = new STNode("Cond");

        STNode gamma2 = new STNode("gamma");
        STNode gamma3 = new STNode("gamma");
        STNode gamma4 = new STNode("gamma");

        n.setLabel("gamma");
        gamma4.setChildren(new ArrayList<>(Arrays.asList(Cond,B)));
        lambda2.setChildren(new ArrayList<>(Arrays.asList(parenthesis,T)));
        gamma3.setChildren(new ArrayList<>(Arrays.asList(gamma4,lambda2)));
        lambda1.setChildren(new ArrayList<>(Arrays.asList(parenthesis,F)));
        gamma2.setChildren(new ArrayList<>(Arrays.asList(gamma3,lambda1)));
        n.setChildren(new ArrayList<>(Arrays.asList(gamma2,nil)));
//        print_cond(n);
        System.out.println(n);
        System.out.println("conditional out");
        return n;
    }

    public void print_cond(STNode node){
        System.out.println("conditional printing");
        ArrayList<STNode> children = new ArrayList<>(Arrays.asList(node));
        while(children.size() > 0){
            STNode current = children.get(0);
            children.remove(0);
            System.out.println(current);
            System.out.println(current.getChildren());
            children.addAll(current.getChildren());
        }
        System.out.println("Conditional over");
    }

}
