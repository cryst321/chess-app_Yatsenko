package validator.field;

import java.util.List;

public class PasswordValidator extends AbstractFieldValidatorHandler {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d_]{8,16}$";

    PasswordValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PasswordValidator INSTANCE = new PasswordValidator(FieldValidatorKey.PASSWORD);
    }

    public static PasswordValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PASSWORD_REGEX)) {
            errors.add("Invalid password");
        }
    }
}