package command;

public interface CommandInterface {
    default String getName(){
        return "default name";
    }
    default String getDescription(){
        return "default description";
    }
    void exe(String arg);
}
