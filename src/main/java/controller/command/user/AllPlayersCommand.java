package controller.command.user;

import constants.Attribute;
import constants.Page;
import controller.command.Command;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllPlayersCommand implements Command{


        private final UserService userService;

        public AllPlayersCommand(UserService userService) {
            this.userService = userService;
        }

        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            List<User> users = userService.getAllUsers();

            request.setAttribute(Attribute.USERS, users);
            return Page.ALL_USERS_VIEW;
        }
    }
