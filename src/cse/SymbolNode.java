package cse;

import definitions.IntegerOperations;
import definitions.StringOperators;
import definitions.TruthValueOperators;
import definitions.TypeIdentificationOperators;

public class SymbolNode implements CSENode {
    private String label;


    public SymbolNode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "SymbolNode{" +
                "label='" + label + '\'' +
                '}';
    }

    @Override
    public void evaluate(CSEMachine cseMachine) {
        switch (this.label){
            case "+":
            case "-":
            case "*":
            case "/":
            case "**":
                IntegerOperations mathop = new IntegerOperations();
                SymbolNode rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                SymbolNode rand2 = (SymbolNode)cseMachine.getStack().remove(0);
                String rand1Val = this.getVal(rand1.getLabel());
                String rand2Val = this.getVal(rand2.getLabel());
                String result = mathop.operate(this.getVal(this.label),rand1Val,rand2Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;
            case "neg":
                mathop = new IntegerOperations();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                result = mathop.operate(this.getVal(this.label),rand1Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;
            case "eq":
            case "ne":
            case "Conc":
                StringOperators stringop = new StringOperators();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand2 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                rand2Val = this.getVal(rand2.getLabel());
                result = stringop.operate(this.getVal(this.label),rand1Val,rand2Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;

            case "Stem":
            case "Stern":
                stringop = new StringOperators();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                result = stringop.operate(this.getVal(this.label),rand1Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;
            case "or":
            case "&":
                TruthValueOperators truthop = new TruthValueOperators();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand2 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                rand2Val = this.getVal(rand2.getLabel());
                result = truthop.operate(this.getVal(this.label),rand1Val,rand2Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;
            case "ls":
                truthop = new TruthValueOperators();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand2 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                rand2Val = this.getVal(rand2.getLabel());
                result = truthop.operate(this.getVal(this.label),rand1Val,rand2Val);
                String resultToBool = "true";
                if(result.equals("0")){
                    resultToBool = "false";
                }
                cseMachine.getStack().add(0,new SymbolNode(resultToBool));
            case "not":
                truthop = new TruthValueOperators();
                rand1 = (SymbolNode)cseMachine.getStack().remove(0);
                rand1Val = this.getVal(rand1.getLabel());
                result = truthop.operate(this.getVal(this.label),rand1Val);
                cseMachine.getStack().add(0,new SymbolNode(result));
                break;
            case "<ID:Isinteger>":
            case "<ID:Istruthvalue>":
            case "<ID:Isstring>":
            case "<ID:Istuple>":
            case "<ID:Isfunction>":
            case "<ID:Print>":
                cseMachine.getStack().add(0,this);
                break;
            default:
                String type = getType(this.label);
                if(type.equals("ID")){
                    System.out.println("In id");
                    CSENode symbol = cseMachine.lookUp(this.label);
                    cseMachine.getStack().add(0,symbol);
                }
                else{
                    cseMachine.getStack().add(0,this);
                }
                break;


        }
    }
    public String getVal(String label){
        int index = label.indexOf(":");
        return label.substring(index+1,label.length()-1);
    }

    public String getType(String label){
        int index = label.indexOf(":");
        return label.substring(1,index);
    }
}
