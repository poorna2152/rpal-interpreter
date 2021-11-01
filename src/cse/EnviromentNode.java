package cse;

public class EnviromentNode implements CSENode{
    Environment environment;

    public EnviromentNode(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().remove(1);
        cseMachine.setCurrentEnv(environment.getParentEnviroment());
    }

    @Override
    public String toString() {
        return "EnviromentNode{" +
                "environment=" + environment +
                '}';
    }
}
