package controller.command.auth;

import constants.Attribute;
import constants.Page;
import constants.ServletPath;
import controller.command.Command;
import controller.utils.HttpWrapper;
import controller.utils.RedirectionManager;
import controller.utils.SessionManager;
import dto.CredentialsDto;
import entity.UserCredentials;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import service.UserCredentialsService;
import validator.entity.CredentialsDtoValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class PostLoginCommand implements Command {

	private final UserCredentialsService userService;
	private static final Logger LOGGER = LogManager.getLogger(UserCredentialsService.class);

	public PostLoginCommand(UserCredentialsService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (SessionManager.getInstance().isUserLoggedIn(session)) {
			RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
			return RedirectionManager.REDIRECTION;
		}

		CredentialsDto credentialsDto = getUserInput(request);
		List<String> errors = validateUserInput(credentialsDto);

		if (!errors.isEmpty()) {
			addRequestAttributes(request, credentialsDto, errors);
			return Page.LOGIN_VIEW;
		}

		Optional<UserCredentials> userCredentials = userService.getFullCredentials(credentialsDto);

		if (userCredentials.isPresent()) {
			LOGGER.info("user found " + userCredentials.get().getNickname() + " " + userCredentials.get().getRole());
			SessionManager.getInstance().addUserToSession(session, userCredentials.get());
			RedirectionManager.getInstance().redirect(new HttpWrapper(request, response), ServletPath.HOME);
			return RedirectionManager.REDIRECTION;
		}
		else {
			LOGGER.info("user not found");
		}
		errors.add("Invalid credentials.");

		addRequestAttributes(request, credentialsDto, errors);
		return Page.LOGIN_VIEW;
	}

	private CredentialsDto getUserInput(HttpServletRequest request) {
		return new CredentialsDto(request.getParameter(Attribute.EMAIL), request.getParameter(Attribute.PASSWORD));
	}

	private List<String> validateUserInput(CredentialsDto credentialsDto) {
		return CredentialsDtoValidator.getInstance().validate(credentialsDto);
	}

	private void addRequestAttributes(HttpServletRequest request, CredentialsDto credentialsDto, List<String> errors) {
		request.setAttribute(Attribute.LOGIN_USER, credentialsDto);
		request.setAttribute(Attribute.ERRORS, errors);
	}
}
