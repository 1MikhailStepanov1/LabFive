package Utility;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private Scanner scanner;
    public Console(Scanner scanner){
        this.scanner = scanner;
    }

    public static void print(Object toOut){
        System.out.print(toOut);
    }
    public static void println(Object toOut){
        System.out.println(toOut);
    }
    public String readln(){
        String line;
        try{
            line = scanner.nextLine();
        }
        catch (NoSuchElementException exception) {
            line = null;
        }
        if (line.length()==0){line = null;}
        return line;
    }
}
