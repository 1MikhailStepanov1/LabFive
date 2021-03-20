package Exceptions;

public class IncorrectValueException extends Exception{
    public IncorrectValueException(String FieldName){
        super(FieldName + "is incorrect.");
    }
    public IncorrectValueException() {super("Value is incorrect.");}
    public IncorrectValueException(String FieldName, String Message){
        super(FieldName + "`s value is incorrect. "+ Message);
    }
}
