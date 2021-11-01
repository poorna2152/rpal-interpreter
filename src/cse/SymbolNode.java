package cse;

public class SymbolNode implements CSENode {
    private String label;

    public SymbolNode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SymbolNode{" +
                "label='" + label + '\'' +
                '}';
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().add(this);
    }
}
