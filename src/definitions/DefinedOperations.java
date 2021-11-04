package definitions;

import cse.CSEMachine;

import java.util.ArrayList;
import java.util.Arrays;

public class DefinedOperations extends  OperationType{

    @Override
    public void handleOperation(String operation, CSEMachine cseMachine) {
        if(operation.equals("Print")){
            System.out.println(cseMachine.getStack().remove(1));
        }
        else{
            super.handleOperation(operation, cseMachine);
        }
    }

}
