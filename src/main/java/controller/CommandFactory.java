package controller;

import controller.command.Command;

class CommandFactory {

    private CommandFactory() {
    }

    static Command getCommand(String commendKey) {
        Command command = CommandEnum.getCommand(commendKey);
        return command;
    }
}