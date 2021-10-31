import java.util.ArrayList;
import java.util.Arrays;

public class StandardizedTree {
    private AST ast;
    private ArrayList<ArrayList<STNode>> sTree = new ArrayList<>();
    private STNode top;
    private ArrayList<STNode> lastHeightNode = new ArrayList<>();



    public StandardizedTree(AST ast) {
        this.ast = ast;
    }

    public void initialize(){
        ArrayList<Node> bottomNodes = ast.getNodes().get(ast.getNodes().size()-1);
        for (int i = 0; i < bottomNodes.size(); i++) {
            STNode node = new STNode(bottomNodes.get(i).getName(),bottomNodes.get(i).getParentIndex());
            lastHeightNode.add(node);
        }
    }

    public void buildTree(){
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
                STNode updated = this.standardize(newNode);
                newLastHeightNodes.add(updated);
            }

            lastHeightNode = newLastHeightNodes;
        }
        System.out.println("Started printing out the structure");
        print_structure(lastHeightNode.get(0));
    }

    public STNode standardize(STNode n){
        STNode updated = n;
        Standardizer standardizer;

        switch (n.getLabel()){
                case "let":
                    standardizer = new LetStandardizer();
                    updated = standardizer.standardize(n);
                break;

                case "tau":
                    standardizer = new TauStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case "function_form":
                    standardizer = new FunctionFormStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case "within":
                    standardizer = new WithinStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case "lambda":
                    standardizer = new MultiParameterStandardizer();
                    updated = standardizer.standardize(n);
                break;

                case "and":
                    standardizer = new AndStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case "rec":
                    standardizer = new RecStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case ",":
                    standardizer = new CommaStandardizer();
                    updated = standardizer.standardize(n);
                    break;
                case "where":
                    standardizer = new WhereStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case "->":
                    standardizer = new ConditionalStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                case"+":
                case "-":
                case "*" :
                case "/":
                case "ls":
                case "ge":
                case "<" :
                case ">":
                    standardizer = new OperatorStandardizer();
                    updated = standardizer.standardize(n);
                    break;
                case "not":
                case "neg":
                    standardizer = new UnaryOperatorStandardizer();
                    updated = standardizer.standardize(n);
                    break;

                    default:
//                System.out.println(n.getLabel());

        }
        return updated;

    }

    void print_structure(STNode node){
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
