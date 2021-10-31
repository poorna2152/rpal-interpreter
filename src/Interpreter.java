import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Interpreter {

    public static void main(String[] args){
        AST ast = new AST();
        ast.buildAST("ast2.txt");
        StandardizedTree standardizedTree = new StandardizedTree(ast);
        standardizedTree.buildTree();
    }
}
