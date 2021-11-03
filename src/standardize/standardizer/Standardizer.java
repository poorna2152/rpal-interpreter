package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

public abstract class Standardizer {
    public Standardizer nextStandardizer =null;
    public void standardize(STNode n, StandardizedTree standardizedTree){
        if(this.nextStandardizer != null){
            nextStandardizer.standardize(n,standardizedTree);
        }
        else{
            standardizedTree.setUpdatedNode(n);
        }
    }

    public void setNextStandardizer(Standardizer nextStandardizer) {
        this.nextStandardizer = nextStandardizer;
    }
}
