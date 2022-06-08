package pks.ftpclient.commands;

import pks.ftpclient.MyException;
import pks.ftpclient.commands.Command;

public class RemoveDirCommand implements Command{
    
    @Override
    public boolean execute() throws MyException{
        return true;
    }
    
}
