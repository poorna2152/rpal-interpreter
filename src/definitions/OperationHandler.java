package definitions;

import cse.CSEMachine;
import cse.node.IdentifierNode;
import cse.node.SymbolNode;

public class OperationHandler {
    private static OperationHandler instance = null;
    private OperationType symbolOperations = null;
    private OperationType identifierOperations = null;


    public OperationHandler() {
        this.initializeIdentifierOperators();
        this.initializeSymbolOperators();
    }

    public static OperationHandler getInstance(){
        if(instance == null){
            instance = new OperationHandler();
        }
        return instance;
    }

    public void initializeSymbolOperators(){
        IntegerOperations integerOperations = new IntegerOperations();
        StringOperators stringOperators = new StringOperators();
        TruthValueOperators truthValueOperators = new TruthValueOperators();

        integerOperations.setNextType(stringOperators);
        stringOperators.setNextType(truthValueOperators);
        this.symbolOperations = integerOperations;
    }

    public void initializeIdentifierOperators(){
        TypeIdentificationOperators typeIdentificationOperators = new TypeIdentificationOperators();
        DefinedOperations definedOperations = new DefinedOperations();
        typeIdentificationOperators.setNextType(definedOperations);
        this.identifierOperations = typeIdentificationOperators;

    }

    public void operateSymbol(String label, CSEMachine machine){
        this.symbolOperations.handleOperation(label,machine);
    }

    public void operateDefined(String label, CSEMachine machine){
        this.identifierOperations.handleOperation(label,machine);

    }
}
