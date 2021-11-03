package cse.node;

import cse.CSEMachine;
import cse.Environment;

public class EnviromentNode implements CSENode{
    Environment environment;

    public EnviromentNode(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        cseMachine.getStack().remove(1);
//        cseMachine.setCurrentEnv(this.environment.getParentEnviroment());
    }

    @Override
    public String toString() {
        return "EnviromentNode{" +
                "environment=" + environment.getNames() +
                '}';
    }
}
