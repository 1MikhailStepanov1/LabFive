package command;

public class CommandAbstract implements CommandInterface{
    private String name;
    private String description;
    public CommandAbstract(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public  String getName(){
        return name;
    }
    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public void exe(String arg) {

    }
}
