package pks.ftpclient.commands;

import pks.ftpclient.MyException;

public interface Command {
    public abstract boolean execute() throws MyException;
}

