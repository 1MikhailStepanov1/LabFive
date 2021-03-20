package command;

import Utility.Receiver;

public class ClearCommand extends CommandAbstract{
    private Receiver receiver;
    public ClearCommand(Receiver receiver){
        super("Clear","Clear collection");
        this.receiver=receiver;
    }
    @Override
    public void exe(String arg){
        receiver.clear();
        System.out.println("Collection has been cleared.");
    }
}
