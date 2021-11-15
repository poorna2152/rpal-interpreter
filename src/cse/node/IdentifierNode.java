package cse.node;

import cse.CSEMachine;

public class IdentifierNode implements CSENode{
    private String label;


    public IdentifierNode(String label) {
        this.label = label;
    }

    /***
     * lookup in the currentEnviroment upwards for a value if not found the IdentifierNode to stack.
     * (This is done because some Defined operations in RPAL AST comes with the Identifier TAG)
     * @param cseMachine
     */
    @Override
    public void evaluate(CSEMachine cseMachine) {
        CSENode symbol = cseMachine.lookUp(this.label);
        if(symbol != null){
            cseMachine.getStack().add(0, symbol);
        }
        else{
            cseMachine.getStack().add(0, this);

        }

    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
