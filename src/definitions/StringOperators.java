package definitions;

public class StringOperators {
    public String operate(String op, String rand1, String rand2){
        String result = "";

        switch (op){
            case "eq":
                result = String.valueOf(rand1.equals(rand1));
                break;
            case "ne":
                result = String.valueOf(!rand1.equals(rand1));
                break;
            case "Stem":
                result = Character.toString(rand1.charAt(0));
                break;
            case "Stern":
                result = Character.toString(rand1.charAt(rand1.length()-1));
                break;
            case "Conc":
                result = rand1+rand2;
                break;
            default:
                System.out.println("Not an integer operator");
        }
        return String.valueOf(result);
    }
}

