package ast;

import java.util.ArrayList;

public class Node {
    private String name;
    private int parentIndex;
    private int height;
    private int index;
    private ArrayList<Integer> childrenIndexes = new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public Node(String name, int height, int index, int parentIndex) {
        this.name = name;
        this.parentIndex = parentIndex;
        this.height= height;
        this.index = index;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public void setChildrenIndexes(int childrenIndex) {
        this.childrenIndexes.add(childrenIndex);
    }

    public ArrayList<Integer> getChildrenIndexes() {
        return childrenIndexes;
    }

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
