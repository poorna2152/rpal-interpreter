import ast.AST;
import cse.CSEMachine;
import cse.PreOrderTraverser;
import standardize.STNode;
import standardize.StandardizedTree;

public class Interpreter {

    public static void main(String[] args){
        AST ast = new AST();
        StandardizedTree standardizedTree = new StandardizedTree(ast);

        ast.buildAST("ast2.txt");
        STNode root = standardizedTree.buildTree();
        CSEMachine cseMachine = new CSEMachine(root);
//        cseMachine.

    }
}
