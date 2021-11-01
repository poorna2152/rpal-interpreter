package definitions;

public class IntegerOperations {
    public String operate(String op, String rand1, String rand2){
        int result = 0;
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
            case "ls":
                boolean resultB = operand1 < operand2;
                if(resultB == true){
                    result =1;
                }
                break;
            default:
                System.out.println("Not an integer operator");
                break;
        }
        return String.valueOf(result);
    }
    public String operate(String op, String rand1){
        int result = Integer.parseInt(rand1);
        switch (op){
            case "neg":
                if(result > 0){
                    result = -result;
                }
                break;

            default:
                System.out.println("Not an integer operator");
                break;
        }
        return "<INT:"+String.valueOf(result)+">";
    }
}
