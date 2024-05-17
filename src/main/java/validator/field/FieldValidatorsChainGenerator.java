package validator.field;

/**
 * Class that chains all the fields validators
 *
 */
public final class FieldValidatorsChainGenerator {

	private FieldValidatorsChainGenerator() {
		
	}

	public static AbstractFieldValidatorHandler getFieldValidatorsChain() {
		AbstractFieldValidatorHandler emailFieldValidator = EmailValidator.getInstance();
		AbstractFieldValidatorHandler passwordFieldValidator = PasswordValidator.getInstance();
		AbstractFieldValidatorHandler nicknameValidator = NicknameValidator.getInstance();

		emailFieldValidator.setNextFieldValidator(passwordFieldValidator);
		passwordFieldValidator.setNextFieldValidator(nicknameValidator);

		return emailFieldValidator;
	}
}
