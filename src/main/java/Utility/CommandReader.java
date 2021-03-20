package Utility;

import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandReader {
    private final Scanner scanner;
    private final Invoker invoker;
    private final Pattern commandNamePattern;
    private final Pattern argPattern;
    public CommandReader(Scanner scan, Invoker inv){
        scanner = scan;
        invoker = inv;
        commandNamePattern = Pattern.compile("^\\w+");
        argPattern = Pattern.compile("\\b(.*\\s*)*");

    }

//TODO Сделать нормальную проверку на аргумент
    public void ActiveMode(){
        String line;
        String command;
        String arg;
        do {
            try {
                line = scanner.nextLine();
            }catch (NoSuchElementException exception){
                break;
            }
            Matcher matcher = commandNamePattern.matcher(line);
            matcher.find();
            try{
                command = matcher.group();
            }
            catch (IllegalStateException e){
                System.out.println("Input is not a command.");
                continue;
            }
            line = line.substring(command.length());
            matcher = argPattern.matcher(line);
            matcher.find();
            try{
                arg = matcher.group();
            }
            catch (IllegalStateException e){
                arg = "";
            }
            invoker.exe(command, arg);
        }while (!invoker.isStopRequested()&& scanner.hasNext());
    }
}
