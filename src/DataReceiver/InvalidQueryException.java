package DataReceiver;

public class InvalidQueryException extends RuntimeException {

    public InvalidQueryException(String message){
        super(message);
    }

    public InvalidQueryException(){
        this("");
    }
}
