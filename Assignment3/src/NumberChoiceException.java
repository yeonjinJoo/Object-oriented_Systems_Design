public class NumberChoiceException extends Exception{
    public NumberChoiceException(String s){
        super(s);
    }
    public String getMessage(){
        return "[NumberChoiceException] " + super.getMessage();
    }
}