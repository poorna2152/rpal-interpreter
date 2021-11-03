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
        TupleOperations tupleOperations = new TupleOperations();

        integerOperations.setNextType(stringOperators);
        stringOperators.setNextType(truthValueOperators);
        truthValueOperators.setNextType(tupleOperations);
        this.symbolOperations = integerOperations;

    }

    public void initializeIdentifierOperators(){
        TypeIdentificationOperators typeIdentificationOperators = new TypeIdentificationOperators();
        DefinedOperations definedOperations = new DefinedOperations();
        TupleOperations tupleOperations = new TupleOperations();

        typeIdentificationOperators.setNextType(definedOperations);
        definedOperations.setNextType(tupleOperations);
        this.identifierOperations = typeIdentificationOperators;

    }

    public void operateSymbol(String label, CSEMachine machine){
        this.symbolOperations.handleOperation(label,machine);
    }

    public void operateDefined(String label, CSEMachine machine){
        System.out.println("label of:" +label);
        this.identifierOperations.handleOperation(label,machine);

    }
}
