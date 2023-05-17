package Week11.Practice1;

public class MyException extends Exception{
    public MyException(String message){
        super(message);
    }

    public String getMesssage(){
        return "[Exception] " + super.getMessage();
    }
}
