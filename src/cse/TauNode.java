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
        System.out.println("tau");
    }
}
