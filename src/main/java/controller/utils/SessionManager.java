package controller.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SessionManager {

	private static final Logger LOGGER = LogManager.getLogger(SessionManager.class);

	static final String USER_HAS_LOGGED_IN = "User has logged in: ";
	static final String USER_HAS_LOGGED_OUT = "User has logged out: ";

	SessionManager() {
	}

	private static final class Holder {
		static final SessionManager INSTANCE = new SessionManager();
	}

	public static SessionManager getInstance() {
		return Holder.INSTANCE;
	}

	/**public boolean isUserLoggedIn(HttpSession session) {
		return session.getAttribute(Attribute.EMPLOYEE) != null;
	}

	public void addUserToSession(HttpSession session, Employee employee) {
		LOGGER.info(String.format(USER_HAS_LOGGED_IN, employee.getEmail()));
		session.setAttribute(Attribute.EMPLOYEE, employee);
	}
	public Employee getUserFromSession(HttpSession session) {
		return (Employee) session.getAttribute(Attribute.EMPLOYEE);
	}

	public void invalidateSession(HttpSession session) {
		if (session != null && session.getAttribute(Attribute.EMPLOYEE) != null) {
			executeSessionInvalidation(session);
		}
	}

	private void executeSessionInvalidation(HttpSession session) {
		Employee employee = (Employee) session.getAttribute(Attribute.EMPLOYEE);
		LOGGER.info(String.format(USER_HAS_LOGGED_OUT, employee.getEmail()));
		session.invalidate();
	}**/
}