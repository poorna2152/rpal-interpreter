import ast.AST;
import cse.CSEMachine;
import standardize.STNode;
import standardize.StandardizedTree;

public class Interpreter {

    public static void main(String[] args){
        boolean printST = false;
        boolean printControls = false;
        String astFile = "";

        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("-st")){
                printST = true;
            }
            else if(args[i].equals(("-controls"))){
                printControls = true;
            }
            else{
                astFile = args[i];
            }
        }

        if(!astFile.equals("")){
            AST ast = new AST();
            StandardizedTree standardizedTree = new StandardizedTree(ast);

            ast.buildAST(astFile);
            STNode root = standardizedTree.buildTree(printST);
            CSEMachine cseMachine = new CSEMachine(root, printControls);
            cseMachine.startEvaluate();
        }
        else{
            System.out.println("File not given");
        }


    }
}
