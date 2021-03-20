package command;


import Utility.Receiver;

public class InfoCommand extends CommandAbstract{
    private final Receiver receiver;
    public InfoCommand(Receiver receiver){
        super("Info", "Show information about collection (type, initialization time and etc.)");
        this.receiver = receiver;
    }
    @Override
    public void exe(String arg) {
        for (String line : receiver.getInfo()){
            System.out.println(line);
        }
    }
}
