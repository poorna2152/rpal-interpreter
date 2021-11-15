package ast;

public class Node {
    private String name;
    private int parentIndex;
    private int height;
    private int index;


    public Node(String name, int height, int index, int parentIndex) {
        this.name = name;
        this.parentIndex = parentIndex;
        this.height= height;
        this.index = index;
    }

    /***
     * Index within the ArrayList of nodes in the same height of the AST.
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /***
     * height of the node (Index of the main ArrayList)
     * @return
     */
    public int getHeight() {
        return height;
    }

    /***
     *
     * @return Label attached with the node
     */

    public String getName() {
        return name;
    }

    /***
     *
     * @return Index of the parent node in the ArrayList of nodes before the current height
     */
    public int getParentIndex() {
        return parentIndex;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", parentIndex=" + parentIndex +
                '}';
    }
}
