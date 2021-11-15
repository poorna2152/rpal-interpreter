package standardize;

import ast.*;
import standardize.standardizer.*;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardizedTree {
    private AST ast;
    private ArrayList<STNode> lastHeightNode = new ArrayList<>();
    private STNode updatedNode;
    private Standardizer standardizer;

    public StandardizedTree(AST ast) {
        this.ast = ast;
    }

    /**
     * Add last nodes of the AST to lastHeightNode arraylist.
     * Initiate the standardizer by calling builder
     */
    public void initialize(){
        StandardizerBuilder standardizerBuilder = new StandardizerBuilder();
        standardizer = standardizerBuilder.buildStandardizer();

        ArrayList<Node> bottomNodes = ast.getNodes().get(ast.getNodes().size()-1);
        for (int i = 0; i < bottomNodes.size(); i++) {
            STNode node = new STNode(bottomNodes.get(i).getName(),bottomNodes.get(i).getParentIndex());
            lastHeightNode.add(node);
        }
    }

    /**
     * Generate the ST
     * @param setPrint: top print the ST
     * @return
     */
    public STNode buildTree(boolean setPrint){
        initialize();
        ArrayList<ArrayList<Node>> astNodes = ast.getNodes();

        for (int i = astNodes.size()-2; i > -1 ; i--) {

            ArrayList<STNode> newLastHeightNodes = new ArrayList<>();

            for (int j = 0; j < astNodes.get(i).size(); j++) {
                Node n = astNodes.get(i).get(j);
                ArrayList<STNode> children = new ArrayList<>();
                for (int k = 0; k < lastHeightNode.size(); k++) {
                    STNode node = lastHeightNode.get(k);
                    if(node.getAstParentIndex() == n.getIndex()){
                        children.add(node);
                    }
                }

                STNode newNode = new STNode(n.getName(),n.getParentIndex());
                newNode.setChildren(children);
                this.standardizer.standardize(newNode, this);
                STNode updated = this.updatedNode;
                newLastHeightNodes.add(updated);
            }
            lastHeightNode = newLastHeightNodes;
        }
        if(setPrint){
            print_structure_dfs(lastHeightNode.get(0),0);
        }
        return lastHeightNode.get(0);
    }

    /**
     * set the updated node in the standardizer
     * @param updatedNode
     */
    public void setUpdatedNode(STNode updatedNode) {
        this.updatedNode = updatedNode;
    }

    /**
     * Print the ST to console using the dfs approach with dots
     * @param node
     * @param currentHeight
     */
    void print_structure_dfs(STNode node, int currentHeight){
        ArrayList<STNode> children = node.getChildren();
        String dots = "";
        for (int i = 0; i < currentHeight; i++) {
            dots+=".";
        }
        System.out.println(dots + node.getLabel());
        int count = 0;
        while(children.size() > count){
            STNode n = children.get(count);
            print_structure_dfs(n,currentHeight+1);
            count++;
        }
    }

    /**
     * Print the tree to console using BFS approach with nodes in same height printed on same line
     * @param node
     */
    void print_structure_bfs(STNode node){
        ArrayList<ArrayList<STNode>> queue = new ArrayList<>();
        ArrayList<ArrayList<String>> printOrder = new ArrayList<>();
        queue.add(new ArrayList<>(Arrays.asList(node)));
        queue.add(new ArrayList<>());

        int parentIndex = 0;
        int childrenIndex = 1;

        ArrayList<STNode> parentsNodes = queue.get(parentIndex);
        ArrayList<STNode> childrenNodes = queue.get(childrenIndex);

        while(parentsNodes.size() > 0){
            ArrayList<String> nextPrintList = new ArrayList<>();
            ArrayList<String> nextEdgeList = new ArrayList<>();

            while(parentsNodes.size() > 0){
                STNode n = parentsNodes.get(0);
                ArrayList<STNode> children = n.getChildren();
                nextPrintList.add("\t");
                nextPrintList.add(n.getLabel());

                if(children.size()==2){
                    nextEdgeList.add("\t");
                    nextEdgeList.add("/");
                    nextEdgeList.add("\\");
                }
                else if(children.size()==0){
                    nextEdgeList.add("\t");
                }
                parentsNodes.remove(0);
                childrenNodes.addAll(children);
            }
            parentsNodes = childrenNodes;
            childrenNodes = new ArrayList<>();

            queue.remove(childrenIndex);
            queue.remove(parentIndex);

            queue.add(parentsNodes);
            queue.add(childrenNodes);

            for (int i = 0; i < nextPrintList.size(); i++) {
                System.out.print(nextPrintList.get(i));
            }
            System.out.println("");
            for (int i = 0; i < nextEdgeList.size(); i++) {
                System.out.print(nextEdgeList.get(i));
            }
            System.out.println("");

        }

    }

}
