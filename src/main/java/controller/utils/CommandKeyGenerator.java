package controller.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Class that is responsible for command key creation from incoming request url
 */
public final class CommandKeyGenerator {

	private static String CONTROLLER_PATH = ".*/chess/";
	private static String REPLACEMENT = "";
	private static String DELIMITER = ":";

	private CommandKeyGenerator() {
	}

	public static String generateCommandKeyFromRequest(HttpServletRequest request) {
		String method = request.getMethod().toUpperCase();
		String path = request.getRequestURI().replaceAll(CONTROLLER_PATH, REPLACEMENT);
		String key = method + DELIMITER + path;
		return key;
	}
}
