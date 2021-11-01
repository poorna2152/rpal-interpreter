package definitions;

public class TruthValueOperators {
    public String operate(String op, String rand1, String rand2){
        boolean result = false;
        boolean operand1 = Boolean.parseBoolean(rand1);
        boolean operand2 = Boolean.parseBoolean(rand2);
        switch (op){
            case "or":
                result = operand1 || operand2;
                break;
            case "&":
                result = operand1 && operand2;
                break;
            case "not":
                result = !operand1;
                break;
            case "eq":
                result = operand1 == operand2;
                break;
            case "ne":
                result = operand1 != operand2;
                break;
            default:
                System.out.println("Not an truth operator");
        }
        return String.valueOf(result);
    }
}
