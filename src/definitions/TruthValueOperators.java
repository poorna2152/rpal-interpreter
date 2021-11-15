package definitions;

import cse.CSEMachine;
import cse.node.BooleanNode;

import java.util.ArrayList;
import java.util.Arrays;

public class TruthValueOperators extends OperationType{
    private final ArrayList<String> binaryOperations = new ArrayList<>(Arrays.asList("or", "&", "eq", "ne"));
    private final ArrayList<String> unaryOperations = new ArrayList<>(Arrays.asList("not"));

    /**
     * Binary operation handling
     */
    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(binaryOperations.contains(operation)){
            if(cseMachine.getStack().get(0) instanceof BooleanNode && cseMachine.getStack().get(1) instanceof BooleanNode){
                BooleanNode rand1 = (BooleanNode)cseMachine.getStack().remove(0);
                BooleanNode rand2 = (BooleanNode)cseMachine.getStack().remove(0);
                boolean result = this.operate(operation,rand1.getValue(),rand2.getValue());
                cseMachine.getStack().add(0,new BooleanNode(result));
            }
        }
        else if(unaryOperations.contains(operation)) {
            if (cseMachine.getStack().get(0) instanceof BooleanNode) {
                BooleanNode rand1 = (BooleanNode) cseMachine.getStack().remove(0);
                boolean result = this.operate(operation, rand1.getValue());
                cseMachine.getStack().add(0,new BooleanNode(result));
            }
        }
        else{
            super.handleOperation(operation,cseMachine);
        }
    }



    public boolean operate(String op, boolean rand1, boolean rand2){
        boolean result = false;
        switch (op){
            case "or":
                result = rand1 || rand2;
                break;
            case "&":
                result = rand1 && rand2;
                break;
            case "not":
                result = !rand1;
                break;
            case "eq":
                result = rand1 == rand2;
                break;
            case "ne":
                result = rand1 != rand2;
                break;
            default:
                System.out.println("Not an truth operator");
        }
        return result;
    }

    public boolean operate(String op, boolean rand1){
        boolean result = false;
        switch (op){
            case "not":
                result = !rand1;
                break;
            default:
                System.out.println("Not an truth operator");
        }
        return result;
    }
}
