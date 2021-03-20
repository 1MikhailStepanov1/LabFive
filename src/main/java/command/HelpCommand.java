package command;

import java.util.HashMap;
import java.util.Map;

//TODO Разобраться как сделать через поле класса
public class HelpCommand extends CommandAbstract{
    private HashMap<String, CommandInterface> commands;
    public HelpCommand(HashMap<String, CommandInterface> command){
        super("help","Show allowed commands");
        commands = command;
    }
    @Override
    public void exe(String arg) {
        for (Map.Entry<String, CommandInterface> entry:commands.entrySet()){
            System.out.println("*"+entry.getKey());
            System.out.println(" " + entry.getValue().getDescription());
        }
    }
}
