package cse;

import cse.node.CSENode;

import java.util.HashMap;

public class Environment {
    private HashMap<String, CSENode> names = new HashMap<String, CSENode>();
    private Environment parentEnviroment;

    public Environment(Environment parentEnviroment) {
        this.parentEnviroment = parentEnviroment;
    }

    /***
     * Add a definition of a name to the the environment
     * @param key: label
     * @param value: CSENode of the value
     */
    public void addName(String key, CSENode value){
        this.names.put(key,value);
    }

    public HashMap<String, CSENode> getNames() {
        return names;
    }

    /***
     * Return the CSENode corresponding to the symbol
     * @param symbol: The symbol whose value is needed
     * @return
     */
    public CSENode getValue(String symbol){
        return names.get(symbol);
    }

    public Environment getParentEnviroment() {
        return parentEnviroment;
    }

    /***
     * Check whether a given symbol is defined in the environment
     * @param symbol: symbol to check
     * @return true if defined false else
     */
    public boolean checkForExistence(String symbol){
        return names.containsKey(symbol);
    }

    @Override
    public String toString() {
        String vars = "";
        for (int i = 0; i < names.size(); i++) {
            vars += names.get(i);

        }
        return "Environment{" +
                "names=" + vars +
                '}';
    }
}
