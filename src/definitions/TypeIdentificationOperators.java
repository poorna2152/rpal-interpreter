package definitions;

import cse.CSEMachine;
import cse.node.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TypeIdentificationOperators extends OperationType{
    private final ArrayList<String> operations = new ArrayList<>(Arrays.asList("Isinteger", "Istruthvalue", "Isstring", "Isfunction", "Isdummy"));

    /**
     * Type identifiers
     */
    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(operations.contains(operation) ){
            CSENode node = cseMachine.getStack().remove(1);
            cseMachine.getStack().remove(0);
            boolean result = this.operate(operation,node);
            cseMachine.getStack().add(0,new BooleanNode(result));
        }
        else{
            super.handleOperation(operation,cseMachine);
        }
    }

    public boolean operate(String op, CSENode node){
        boolean result = false;
        switch (op){
            case "Isinteger":
                result = node instanceof IntegerNode;
                break;
            case "Istruthvalue":
                result = node instanceof BooleanNode;
                break;
            case "Isstring":
                result = node instanceof StringNode;
                break;
            case "Istuple":
                result = node instanceof TauNode;
                break;
            case "Isfunction":
                result = node instanceof LambdaNode;
                break;

            case "Isdummy":
                result = node instanceof DummyNode;;
                break;
            default:
                System.out.println("Not an type identification operator");
        }
        return result;
    }
}
