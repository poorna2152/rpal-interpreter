package definitions;

import cse.CSEMachine;
import cse.node.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TupleOperations extends OperationType {
    private final ArrayList<String> binaryOperations = new ArrayList<>(Arrays.asList("aug"));
    private final ArrayList<String> unaryOperations = new ArrayList<>(Arrays.asList("Null", "Order", "<nil>"));

    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(binaryOperations.contains(operation)){
            if(cseMachine.getStack().get(0) instanceof TupleNode){
                TupleNode rand1 = (TupleNode) cseMachine.getStack().get(0);
                CSENode rand2 = cseMachine.getStack().remove(1);
                this.addToTuple(operation,rand1, rand2);
            }
        }
        else if(unaryOperations.contains(operation)) {
            if(cseMachine.getStack().get(0) instanceof TupleNode){
                TupleNode rand1 = (TupleNode) cseMachine.getStack().remove(0);
                if(operation.equals("Order")) {
                    cseMachine.getStack().add(0, new IntegerNode(rand1.getChildren().size()));
                }else if(operation.equals("Null")) {
                    cseMachine.getStack().add(0, new BooleanNode(rand1.getChildren().size() == 0));
                }
            }else if(operation.equals("<nil>")){
                    System.out.println("in nil node");
                    cseMachine.getStack().add(0, new TupleNode(new ArrayList<>()));
            }
        }
        else{
            super.handleOperation(operation,cseMachine);
        }
    }


    public void addToTuple(String op, TupleNode rand1, CSENode rand2){
        switch (op){
            case "aug":
                rand1.getChildren().add(rand2);
                break;
            default:
                System.out.println("Invalid tuple operation");
        }
    }
}
