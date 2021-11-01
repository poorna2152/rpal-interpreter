package cse;

public class TauNode implements CSENode {
    private int childrenCount;

    @Override
    public String toString() {
        return "TauNode{" +
                "childrenCount=" + childrenCount +
                '}';
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public TauNode(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        String tauVal = "<ID:STR:(";
        for (int i = 0; i < childrenCount; i++) {
            SymbolNode node =  (SymbolNode)cseMachine.getStack().remove(0);
            tauVal += node.getVal(node.getLabel())+ ",";

        }
        tauVal = tauVal.substring(0,tauVal.length()-1);
        tauVal +=")>";
        cseMachine.getStack().add(0,new SymbolNode(tauVal));
    }
}
