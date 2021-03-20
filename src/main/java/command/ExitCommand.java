package command;


import Utility.Invoker;

public class ExitCommand extends CommandAbstract{
    private final Invoker inv;
    public ExitCommand(Invoker invoker){
        super("Exit", "End programme without saving to the file");
        inv=invoker;
    }
    @Override
    public void exe(String arg){
        inv.RequestExit(this);
    }
}
