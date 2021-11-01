package definitions;

public class TypeIdentificationOperators {
    public String operate(String op, String rand1, String rand2){
        String result = "";
        switch (op){
            case "Isinteger":
                result = String.valueOf(rand1.equals(rand1));
                break;
            case "Istruthvalue":
                result = String.valueOf(!rand1.equals(rand1));
                break;
            case "Isstring":
                result = Character.toString(rand1.charAt(0));
                break;
            case "Istuple":
                result = Character.toString(rand1.charAt(rand1.length()-1));
                break;
            case "Isfunction":
                result = rand1+rand2;
                break;

            case "Isdummy":
                result = rand1+rand2;
                break;
            default:
                System.out.println("Not an integer operator");
        }
        return String.valueOf(result);
    }
}
