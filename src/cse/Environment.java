package cse;

import java.util.HashMap;

public class Environment {
    private HashMap<String, String> names = new HashMap<String, String>();
    private Environment parentEnviroment;

    public Environment(Environment parentEnviroment) {
        this.parentEnviroment = parentEnviroment;
    }

    public void addName(String key, String value){
        this.names.put(key,value);
    }

    public String getValue(String symbol){
        return names.get(symbol);
    }

    public Environment getParentEnviroment() {
        return parentEnviroment;
    }

    public boolean checkForExistence(String symbol){
        return names.containsKey(symbol);
    }
}
