package validator.entity;

import dto.CredentialsDto;
import validator.field.AbstractFieldValidatorHandler;
import validator.field.FieldValidatorKey;
import validator.field.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

/**Валідує поля при логіні**/

public final class CredentialsDtoValidator implements Validator<CredentialsDto> {

	private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

	CredentialsDtoValidator() {
	}

	private static class Holder {
		static final CredentialsDtoValidator INSTANCE = new CredentialsDtoValidator();
	}

	public static CredentialsDtoValidator getInstance() {
		return Holder.INSTANCE;
	}

	@Override
	public List<String> validate(CredentialsDto dto) {
		List<String> errors = new ArrayList<>();

		fieldValidator.validateField(FieldValidatorKey.EMAIL, dto.getEmail(), errors);
		fieldValidator.validateField(FieldValidatorKey.PASSWORD, dto.getPassword(), errors);

		return errors;
	}
}