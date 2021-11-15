package standardize.standardizer;

import standardize.STNode;
import standardize.StandardizedTree;

public abstract class Standardizer {
    public Standardizer nextStandardizer =null;

    /***
     * Generate the Standardizer chain for standardizing the AST.
     * In default case if the nextStandardizer is null then add the same node back to STree
     * @param n
     * @param standardizedTree
     */
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
