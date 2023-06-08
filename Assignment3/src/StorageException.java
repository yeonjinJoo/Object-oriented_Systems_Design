public class StorageException extends Exception{
    public StorageException(String message){
        super(message);
    }

    public String getMessage(){
        return "[StorageException] " + super.getMessage();
    }
}
