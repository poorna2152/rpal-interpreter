import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AST {
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<>();

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
//                    nodes.get(dotCount-1).get(parentIndex).setChildrenIndexes(0);
                    cHeight = dotCount;
                }
                else {
                    int index = nodes.get(dotCount).size();
                    nodes.get(dotCount).add(new Node(data.substring(dotCount), dotCount, index, parentIndex));
//                    nodes.get(dotCount-1).get(parentIndex).setChildrenIndexes(nodes.get(dotCount).size()-1);
                }

                //System.out.println(dotCount);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Node getParent(int height, int index){
        return nodes.get(height).get(index);
    }

    public ArrayList<Node> getChildren(Node parentN){
        ArrayList<Node> children = new ArrayList<>();
        for (Node n:nodes.get(parentN.getHeight()+1)) {
            if(n.getParentIndex() == parentN.getIndex()){
                children.add(n);
            }
        }
        return children;
    }

    public ArrayList<ArrayList<Node>> getNodes() {
        return nodes;
    }
}
