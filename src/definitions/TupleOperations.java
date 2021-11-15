package definitions;

import cse.CSEMachine;
import cse.node.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TupleOperations extends OperationType {
    private final ArrayList<String> binaryOperations = new ArrayList<>(Arrays.asList("aug"));
    private final ArrayList<String> unaryOperations = new ArrayList<>(Arrays.asList("Null", "Order", "<nil>"));

    /**
     * Tuple operations:
     * nil: Initiate a new Tuple (empty)
     * Order: get length of a tuple
     * Null: check if tuple is nil
     * aug: add next in stack to tuple
     * @param operation
     * @param cseMachine
     */
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
            if(operation.equals("<nil>")){
                cseMachine.getStack().add(0, new TupleNode(new ArrayList<>()));
            }
            else if(cseMachine.getStack().get(1) instanceof TupleNode) {
                if (operation.equals("Order")) {
                    TupleNode rand1 = (TupleNode) cseMachine.getStack().remove(1);
                    cseMachine.getStack().add(0, new IntegerNode(rand1.getChildren().size()));
                    cseMachine.getStack().remove(1);
                }else if(operation.equals("Null")) {
                    TupleNode rand1 = (TupleNode) cseMachine.getStack().remove(1);
                    cseMachine.getStack().add(0, new BooleanNode(rand1.getChildren().size() == 0));
                }

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
