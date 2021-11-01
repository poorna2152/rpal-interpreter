package definitions;

public class IntegerOperations {
    public String operate(String op, String rand1, String rand2){
        double result = 0;
        int operand1 = Integer.parseInt(rand1);
        int operand2 = Integer.parseInt(rand2);
        switch (op){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1/operand2;
                break;
            case "**":
                result = (int)Math.pow(operand1,operand2);
                break;
            default:
                System.out.println("Not an integer operator");
        }
        return String.valueOf(result);
    }
}
