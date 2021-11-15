package standardize;

import java.util.ArrayList;

public class STNode {
    private int astParentIndex;
    private String label;
    private boolean revisit = false;
    private ArrayList<STNode> children = new ArrayList<>();

    /**
     * Children stored in children array which is later used in generating control structures
     */
    public STNode(String label) {
        this.label = label;
    }

    public STNode(String label,int astParentIndex) {
        this.astParentIndex = astParentIndex;
        this.label = label;
    }


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

    public void addChild(STNode node){
        this.children.add(node);
    }


    public String getLabel() {
        return label;
    }

    public int getAstParentIndex() {
        return astParentIndex;
    }

    public void setRevisit(boolean revisit) {
        this.revisit = revisit;
    }

    public boolean isRevisit() {
        return revisit;
    }

    @Override
    public String toString() {
        return  label;
    }
}
