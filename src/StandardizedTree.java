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

//            System.out.println("Height over");
//            System.out.println(newLastHeightNodes);
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
//            case "rec":
//                System.out.println("rec");
//                break;

//            case ",":
//                System.out.println(",");
//                break;
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
        ArrayList<STNode> queue = new ArrayList<>(Arrays.asList(node));
        boolean over =false;
        int height = 0;
        int tabCount = 0;
        while(!over){
            if(queue.size()>0){
                STNode n = queue.get(0);
                ArrayList<STNode> children = n.getChildren();
                queue.remove(0);
                System.out.println(n);
                System.out.println("├── ");
//                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                System.out.println(children);
                for (int i = 0; i < children.size(); i++) {
                    queue.add(children.get(i));
                }
            }else{
                over = true;
            }

        }

    }

}
