package cse;

public class LambdaNode extends CSENode {
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

    public LambdaNode(String label, int index, String boundVariable) {
        super(label);
        this.index = index;
        this.boundVariable = boundVariable;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public Environment getEnv() {
        return env;
    }
}
