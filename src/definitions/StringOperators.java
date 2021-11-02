package definitions;

import cse.CSEMachine;
import cse.node.BooleanNode;
import cse.node.StringNode;

import java.util.ArrayList;
import java.util.Arrays;

public class StringOperators extends  OperationType{
    private final ArrayList<String> binaryOperations = new ArrayList<>(Arrays.asList("Conc"));
    private final ArrayList<String> binaryComparisonOperations = new ArrayList<>(Arrays.asList("eq", "ne"));
    private final ArrayList<String> unaryOperations = new ArrayList<>(Arrays.asList("Stem", "Stern"));

    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(binaryOperations.contains(operation) || binaryComparisonOperations.contains(operation)){
            if(cseMachine.getStack().get(0) instanceof StringNode && cseMachine.getStack().get(1) instanceof StringNode){
                StringNode rand1 = (StringNode)cseMachine.getStack().remove(0);
                StringNode rand2 = (StringNode)cseMachine.getStack().remove(0);
                if(binaryOperations.contains(operation)){
                    String result = this.operate(operation,rand1.getValue(),rand2.getValue());
                    cseMachine.getStack().add(0,new StringNode(result));
                }else{
                    boolean result = this.operateBool(operation,rand1.getValue(),rand2.getValue());
                    cseMachine.getStack().add(0,new BooleanNode(result));
                }
            }
        }
        else if(unaryOperations.contains(operation)) {
            if (cseMachine.getStack().get(0) instanceof StringNode) {
                StringNode rand1 = (StringNode) cseMachine.getStack().remove(0);
                String result = this.operate(operation, rand1.getValue());
                cseMachine.getStack().add(0,new StringNode(result));
            }
        }
        else{
            super.handleOperation(operation,cseMachine);
        }
    }


    public String operate(String op, String rand1, String rand2){
        String result = "";
        switch (op){
            case "Conc":
                result = rand1+rand2;
                break;
            default:
                System.out.println("Invalid string operation");
        }
        return result;
    }

    public boolean operateBool(String op, String rand1, String rand2){
        boolean result = false;
        switch (op){
            case "eq":
                result = rand1.equals(rand2);
                break;
            case "ne":
                result = !rand1.equals(rand2);
                break;

                default:
                System.out.println("Invalid string operation");
        }
        return result;
    }

    public String operate(String op, String rand1){
        String result = "";

        switch (op){
            case "Stem":
                result = Character.toString(rand1.charAt(0));
                break;
            case "Stern":
                result = Character.toString(rand1.charAt(rand1.length()-1));
                break;
            default:
                System.out.println("Invalid String operation");
        }
        return result;
    }
}

