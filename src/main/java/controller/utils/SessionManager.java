package controller.utils;

import constants.Attribute;
import entity.UserCredentials;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

	private static final Logger LOGGER = LogManager.getLogger(SessionManager.class);

	private static final Map<Integer, HttpSession> activeSessions = new HashMap<>();

	static final String USER_HAS_LOGGED_IN = "User has logged in: %s";
	static final String USER_HAS_LOGGED_OUT = "User has logged out: %s";

	SessionManager() {
	}

	private static final class Holder {
		static final SessionManager INSTANCE = new SessionManager();
	}

	public static SessionManager getInstance() {
		return Holder.INSTANCE;
	}

	public boolean isUserLoggedIn(HttpSession session) {
		return session.getAttribute(Attribute.USER_CREDENTIALS) != null;
	}

	public void addUserToSession(HttpSession session, UserCredentials userCredentials) {
		LOGGER.info(String.format(USER_HAS_LOGGED_IN, userCredentials.getEmail()));
		session.setAttribute(Attribute.USER_CREDENTIALS, userCredentials);
		activeSessions.put(userCredentials.getId(), session);

	}
	public UserCredentials getUserFromSession(HttpSession session) {
		return (UserCredentials) session.getAttribute(Attribute.USER_CREDENTIALS);
	}

	public HttpSession getSessionByUserId(Integer userId) {
		return activeSessions.get(userId);
	}


	public void removeSession(UserCredentials user) {
		activeSessions.remove(user.getId());
	}

	public void invalidateSession(HttpSession session) {
		if (session != null && session.getAttribute(Attribute.USER_CREDENTIALS) != null) {
			executeSessionInvalidation(session);
		}
	}

	private void executeSessionInvalidation(HttpSession session) {
		UserCredentials userCredentials = (UserCredentials) session.getAttribute(Attribute.USER_CREDENTIALS);
		LOGGER.info(String.format(USER_HAS_LOGGED_OUT, userCredentials.getEmail()));
		session.invalidate();
	}
}