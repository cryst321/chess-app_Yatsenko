package validator.entity;

import dto.CredentialsDto;
import entity.UserCredentials;
import validator.field.AbstractFieldValidatorHandler;
import validator.field.FieldValidatorKey;
import validator.field.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Валідує дані при реєстрації
 */
public class UserCredentialsValidator implements Validator<UserCredentials>{

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    UserCredentialsValidator() {
    }

    private static class Holder {
        static final UserCredentialsValidator INSTANCE = new UserCredentialsValidator();
    }

    public static UserCredentialsValidator getInstance() {
        return UserCredentialsValidator.Holder.INSTANCE;
    }

    @Override
    public List<String> validate(UserCredentials dto) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.EMAIL, dto.getEmail(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, dto.getPassword(), errors);
        fieldValidator.validateField(FieldValidatorKey.NICKNAME, dto.getNickname(), errors);

        return errors;
    }
}
