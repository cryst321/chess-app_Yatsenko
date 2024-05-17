package controller.command.auth;

import constants.ServletPath;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import controller.utils.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionManager.getInstance().invalidateSession(request.getSession());
		RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
		return RedirectionManager.REDIRECTION;
	}
}
