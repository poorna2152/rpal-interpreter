package definitions;

import cse.CSEMachine;
import cse.node.BooleanNode;
import cse.node.IntegerNode;

import java.util.ArrayList;
import java.util.Arrays;

public class IntegerOperations extends OperationType {
    private final ArrayList<String> binaryOperations = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "**"));
    private final ArrayList<String> binaryComparisonOperations = new ArrayList<>(Arrays.asList("eq", "ne", "ls", "<", "gr", ">", "le", "<=", "ge", ">="));
    private final ArrayList<String> unaryOperations = new ArrayList<>(Arrays.asList("neg"));

    /**
     * Handle binary and unary boolean operators
     * @param operation
     * @param cseMachine
     */
    @Override
    public void handleOperation(String operation,CSEMachine cseMachine) {
        if(binaryOperations.contains(operation) || binaryComparisonOperations.contains(operation)){
            if(cseMachine.getStack().get(0) instanceof IntegerNode && cseMachine.getStack().get(1) instanceof IntegerNode){
                IntegerNode rand1 = (IntegerNode)cseMachine.getStack().remove(0);
                IntegerNode rand2 = (IntegerNode)cseMachine.getStack().remove(0);
                if(binaryOperations.contains(operation)){
                    int result = this.operateInt(operation,rand1.getValue(),rand2.getValue());
                    cseMachine.getStack().add(0,new IntegerNode(result));
                }else{
                    boolean result = this.operateBool(operation,rand1.getValue(),rand2.getValue());
                    cseMachine.getStack().add(0,new BooleanNode(result));
                }
            }
            else{
                super.handleOperation(operation,cseMachine);
            }
        }
        else if(unaryOperations.contains(operation)) {
            if (cseMachine.getStack().get(0) instanceof IntegerNode) {
                IntegerNode rand1 = (IntegerNode) cseMachine.getStack().remove(0);
                int result = this.operateInt(operation, rand1.getValue());
                cseMachine.getStack().add(0,new IntegerNode(result));
            }
        }
        else{
                super.handleOperation(operation,cseMachine);
            }
    }

    /***
     * Binary operator definitions
     */
    public int operateInt(String op, int rand1, int rand2){
        int result = 0;
        switch (op){
            case "+":
                result = rand1 + rand2;
                break;
            case "-":
                result = rand1 - rand2;
                break;
            case "*":
                result = rand1 * rand2;
                break;
            case "/":
                result = rand1/rand2;
                break;
            default:
                System.out.println("Not an integer operator");
                break;
            case "**":
                result = (int)Math.pow(rand1,rand2);
                break;
        }
        return result;
    }

    /***
     * Unary operator definitions
     */
    public int operateInt(String op, int rand1){
        int result = rand1;
        switch (op){
            case "neg":
                result = -result;
                break;

            default:
                System.out.println("Not an integer operator");
                break;
        }
        return result;
    }

    /***
     * Comparison operator definitions
     */
    public boolean operateBool(String op, int rand1, int rand2){
        boolean result = false;
        switch (op){
            case "ls":
            case "<":
                result = rand1 < rand2;
                break;
            case "le":
            case "<=":
                result = rand1 <= rand2;
                break;
            case "gr":
            case ">":
                result = rand1 > rand2;
                break;
            case "ge":
            case ">=":
                result = rand1 >= rand2;
                break;
            case "eq":
                result = rand1 == rand2;
                break;
            case "ne":
                result = rand1 != rand2;
                break;
            default:
                System.out.println("Not an integer operator");
                break;
        }
        return result;
    }

}
