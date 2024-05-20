package constants;

public final class Page {
	
	public static final String PREFIX = "/WEB-INF/views/";
	public static final String ERROR_PREFIX = "errors";
	public static final String SUFFIX = ".jsp";

	private Page() {
	}

	public static final String HOME_VIEW = "/index" + SUFFIX;	
	public static final String LOGIN_VIEW = PREFIX + "login" + SUFFIX;
	public static final String SIGNUP_VIEW = PREFIX + "signup" + SUFFIX;
	public static final String CREATEGAME_VIEW = PREFIX + "creategame" + SUFFIX;
	public static final String PROFILE_VIEW = PREFIX + "profile" + SUFFIX;



/**User*/
	public static final String ALL_USERS_VIEW = PREFIX + "players" + SUFFIX;
	public static final String UPDATE_PROFILE = PREFIX + "updateProfile" + SUFFIX;

	/**Complaint*/
	public static final String REPORT_VIEW = PREFIX + "report" + SUFFIX;
	public static final String ALL_REPORTS_VIEW = PREFIX + "reports" + SUFFIX;


	public static final String ALL_CREDENTIALS_VIEW = PREFIX + "credentials" + SUFFIX;


	public static final String PAGE_NOT_FOUND = PREFIX + ERROR_PREFIX + "pageNotFound" + SUFFIX;	
}