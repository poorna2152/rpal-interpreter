import ast.AST;
import cse.CSEMachine;
import standardize.STNode;
import standardize.StandardizedTree;

public class Interpreter {

    public static void main(String[] args){
        AST ast = new AST();
        StandardizedTree standardizedTree = new StandardizedTree(ast);

        ast.buildAST("ast");
        STNode root = standardizedTree.buildTree();
        CSEMachine cseMachine = new CSEMachine(root);
//        cseMachine.

    }
}
