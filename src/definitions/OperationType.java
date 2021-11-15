package definitions;

import cse.CSEMachine;

public class OperationType {
    private OperationType nextType = null;

    /**
     * Default behaviour of operations
     * if no nextType then exception.
     */
    public void handleOperation(String operation, CSEMachine cseMachine){
        if(this.nextType != null){
            this.nextType.handleOperation(operation,cseMachine);
        }else{
            System.out.println("Uncaught operation type");
        }
    }

    public void setNextType(OperationType operationType){
        this.nextType = operationType;
    }
}
