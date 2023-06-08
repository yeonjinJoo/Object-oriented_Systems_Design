public class ContactNotFoundException extends Exception{
    public ContactNotFoundException(){
        super("Contact not exist.");
    }

    public String getMessage(){
        return "[ContactNotFoundException] " + super.getMessage();
    }
}
