package definitions;

import cse.CSEMachine;

public class DefinedOperations extends  OperationType{
    /**
     * Print function handle. Use the toString functions in the CSENode to print the value to console.
     * @param operation
     * @param cseMachine
     */
    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(operation.equals("Print")){
            System.out.println(cseMachine.getStack().get(1));
            cseMachine.getStack().remove(0);

        }
        else{
            super.handleOperation(operation, cseMachine);
        }
    }

}
