import java.util.ArrayList;

public class STNode {
    private int astParentIndex;
    private int astIndex;
    private String label;
    private ArrayList<STNode> children = new ArrayList<>();

    public ArrayList<STNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<STNode> children) {
        this.children = children;
    }

    public void setAstParentIndex(int astParentIndex) {
        this.astParentIndex = astParentIndex;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public STNode(String label) {
        this.label = label;
    }

    public STNode(String label,int astParentIndex) {
        this.astParentIndex = astParentIndex;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public int getAstParentIndex() {
        return astParentIndex;
    }

    @Override
    public String toString() {
        return "STNode{" +
                "label='" + label + '\'' +
                '}';
    }
}
