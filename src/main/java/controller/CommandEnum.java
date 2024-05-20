package controller;

import controller.command.Command;
import controller.command.HomeCommand;
import controller.command.PageNotFoundCommand;
import controller.command.auth.*;
import controller.command.complaint.AllReportsCommand;
import controller.command.complaint.BookComplaintCommand;
import controller.command.complaint.GetReportCommand;
import controller.command.complaint.PostReportCommand;
import controller.command.game.GetCreateGameCommand;
import controller.command.game.GetLobbyCommand;
import controller.command.game.PostCreateGameCommand;
import controller.command.user.AllPlayersCommand;
import controller.command.user.GetProfileCommand;
import controller.command.user.GetUpdateUserCommand;
import controller.command.user.PostUpdateUserCommand;
import controller.command.user_credentials.AllUserCredentialsCommand;
import service.ComplaintService;
import service.GameRequestService;
import service.UserCredentialsService;
import service.UserService;

import java.awt.print.Book;

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
    GET_CREATE_GAME_REQUEST {
        {
            this.key = "GET:createGame";
            this.command = new GetCreateGameCommand();
        }
    },
    POST_CREATE_GAME {
        {
            this.key = "POST:createGame";
            this.command = new PostCreateGameCommand(GameRequestService.getInstance(), UserService.getInstance());
        }
    },
    GET_PROFILE {
            {
                this.key = "GET:profile";
                this.command = new GetProfileCommand(UserService.getInstance());
            }

    },
    ALL_PLAYERS {
        {
            this.key = "GET:players";
            this.command = new AllPlayersCommand(UserService.getInstance());
        }

    },
    GET_UPDATE_PROFILE {
        {
            this.key = "GET:updateProfile";
            this.command = new GetUpdateUserCommand(UserService.getInstance());
        }

    },
    POST_UPDATE_PROFILE {
        {
            this.key = "POST:updateProfile";
            this.command = new PostUpdateUserCommand(UserService.getInstance());
        }

    },
    GET_LOBBY {
        {
            this.key = "GET:lobby";
            this.command = new GetLobbyCommand(GameRequestService.getInstance());

        }
    },

    MODERATION {
        {
            this.key = "GET:moderation";
            this.command = new AllReportsCommand(ComplaintService.getInstance());

        }
    },
    GET_REPORT {
        {
            this.key = "GET:report";
            this.command = new GetReportCommand(UserService.getInstance());
        }
    },

    REPORT_USER {
        {
            this.key = "POST:report";
            this.command = new PostReportCommand(ComplaintService.getInstance(), UserService.getInstance());
        }
    },

    BOOK_COMPLAINT {
        {
            this.key = "GET:bookComplaint";
            this.command = new BookComplaintCommand(ComplaintService.getInstance());

        }
    }
    ;

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
