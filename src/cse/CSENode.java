package cse;

public class CSENode {
    private String label;

    public CSENode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label ;
    }
}
