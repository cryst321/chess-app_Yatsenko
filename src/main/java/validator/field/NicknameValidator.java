package validator.field;

import java.util.List;

public class NicknameValidator extends AbstractFieldValidatorHandler{

    private static final String NICKNAME_REGEX = "^[a-zA-Z0-9]([a-zA-Z0-9-_]{1,18}[a-zA-Z0-9])?$";

    NicknameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final NicknameValidator INSTANCE = new NicknameValidator(FieldValidatorKey.NICKNAME);
    }

    public static NicknameValidator getInstance() {
        return NicknameValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(NICKNAME_REGEX)) {
            errors.add("Invalid nickname");
        }
    }
}
