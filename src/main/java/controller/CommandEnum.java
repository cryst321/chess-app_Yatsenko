package controller;

import controller.command.Command;
import controller.command.HomeCommand;
import controller.command.PageNotFoundCommand;
import controller.command.auth.*;
import controller.command.user_credentials.AllUserCredentialsCommand;
import service.UserCredentialsService;

enum CommandEnum {

    PAGE_NOT_FOUND {
        {
            this.key = "GET:pageNotFound";
            this.command = new PageNotFoundCommand();
        }
    },

    HOME {
        {
            this.key = "GET:";
            this.command = new HomeCommand();
        }
    },

    GET_LOGIN {
        {
            this.key = "GET:login";
            this.command = new GetLoginCommand();
        }
    },
    LOGOUT {
        {
            this.key = "GET:logout";
            this.command = new LogoutCommand();
        }
    },
    POST_LOGIN {
        {
            this.key = "POST:login";
            this.command = new PostLoginCommand(UserCredentialsService.getInstance());
        }
    },
    GET_SIGNUP {
        {
            this.key = "GET:signup";
            this.command = new GetSignupCommand();
        }
    },
    POST_SIGNUP {
        {
            this.key = "POST:signup";
            this.command = new PostSignupCommand(UserCredentialsService.getInstance());
        }
    },
    ALL_USER_CREDENTIALS {
        {
            this.key = "GET:credentials";
            this.command = new AllUserCredentialsCommand(UserCredentialsService.getInstance());
        }
    },
    GET_CREATEGAME {
        {
            this.key = "GET:creategame";
            this.command = new GetCreategameCommand();
        }
    },
    GET_PROFILE {
            {
                this.key = "GET:profile";
                this.command = new GetProfileCommand();
            }

    }
    ;

    String key;
    Command command;

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (final CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
