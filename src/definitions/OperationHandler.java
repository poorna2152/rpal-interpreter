package definitions;

import cse.CSEMachine;

public class OperationHandler {
    private static OperationHandler instance = null;
    private OperationType symbolOperations = null;
    private OperationType identifierOperations = null;


    public OperationHandler() {
        this.initializeIdentifierOperators();
        this.initializeSymbolOperators();
    }

    /**
     * Get a instance of a singleton class
     * @return
     */

    public static OperationHandler getInstance(){
        if(instance == null){
            instance = new OperationHandler();
        }
        return instance;
    }

    /**
     * Initialize the integer , string, truth value and tuple operations
     */
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

    /**
     * Initialize the IDENTIFIER operations. operations with a <ID: tag
     */
    public void initializeIdentifierOperators(){
        TypeIdentificationOperators typeIdentificationOperators = new TypeIdentificationOperators();
        DefinedOperations definedOperations = new DefinedOperations();
        TupleOperations tupleOperations = new TupleOperations();
        StringOperators stringOperators = new StringOperators();

        typeIdentificationOperators.setNextType(definedOperations);
        definedOperations.setNextType(tupleOperations);
        tupleOperations.setNextType(stringOperators);
        this.identifierOperations = typeIdentificationOperators;

    }

    /**
     * Handle integer, string,tuple and truthvalue operations
     */
    public void operateSymbol(String label, CSEMachine machine){
        this.symbolOperations.handleOperation(label,machine);
    }


    /**
     * Handle defined operations
     */
    public void operateDefined(String label, CSEMachine machine){
        this.identifierOperations.handleOperation(label,machine);

    }
}
