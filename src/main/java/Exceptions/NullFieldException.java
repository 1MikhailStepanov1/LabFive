package Exceptions;

public class NullFieldException extends Exception{
    public NullFieldException (String FieldName){
        super(FieldName + "can`t be null.");
    }
}
