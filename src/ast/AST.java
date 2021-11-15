package ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AST {
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<>();

    /***
     * Generate the AST Structure
     * @param filepath: Input file file path
     */
    public void buildAST(String filepath){
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);

            int cHeight = -1;
            int parentIndex = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int dotCount = (int)data.chars().filter(ch -> ch == '.').count();
                //
                if(cHeight!=-1){
                    parentIndex = nodes.get(dotCount-1).size() - 1;
                }

                if(dotCount > cHeight){
                    nodes.add(new ArrayList<Node>());
                    nodes.get(dotCount).add(new Node(data.substring(dotCount), dotCount, 0, parentIndex));
                    cHeight = dotCount;
                }
                else {
                    int index = nodes.get(dotCount).size();
                    nodes.get(dotCount).add(new Node(data.substring(dotCount), dotCount, index, parentIndex));
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /***
     *
     * Return immediate children of the given node in the AST
     * @param parentN node to find parent of
     * @return ArrayList of children nodes
     */
    public ArrayList<Node> getChildren(Node parentN){
        ArrayList<Node> children = new ArrayList<>();
        for (Node n:nodes.get(parentN.getHeight()+1)) {
            if(n.getParentIndex() == parentN.getIndex()){
                children.add(n);
            }
        }
        return children;
    }

    /***
     * Return the 2D Array list of nodes in AST with each ArrayList inside the main ArrayList representing nodes of the
     * same height of the AST
      * @return Tree structure
     */
    public ArrayList<ArrayList<Node>> getNodes() {
        return nodes;
    }
}
