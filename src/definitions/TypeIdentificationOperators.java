package definitions;

public class TypeIdentificationOperators {
    public String operate(String op, String rand1){
        System.out.println(op);
        boolean result = false;
        switch (op){
            case "Isinteger":
                result = rand1.equals("INT");
                break;
            case "Istruthvalue":
                result = rand1.equals("TRUTH");
                break;
            case "Isstring":
                result = rand1.equals("STR");
                break;
            case "Istuple":
                result = rand1.equals("TUP");
                break;
            case "Isfunction":
                result = rand1.equals("FUNC");
                break;

            case "Isdummy":
                result = rand1.equals("DUMMY");;
                break;
            default:
                System.out.println("Not an integer operator");
        }
        return Boolean.toString(result);
    }
}
