package cse;

public class LambdaNode implements CSENode {
    private int index;
    private String boundVariable;
    private Environment env;

    @Override
    public String toString() {
        return "LambdaNode{" +
                "index=" + index +
                ", boundVariable='" + boundVariable + '\'' +
                '}';
    }

    public LambdaNode( int index, String boundVariable) {
        this.index = index;
        this.boundVariable = boundVariable;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        this.setEnv(cseMachine.getCurrentEnv());
        cseMachine.getStack().add(0,this);
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public Environment getEnv() {
        return env;
    }

    public String getBoundVariable() {
        return boundVariable;
    }
}
