package validator.field;

public enum FieldValidatorKey {

    NICKNAME("nickname"), EMAIL("email"), PASSWORD("password");

   private String value;

    FieldValidatorKey(String value) {
    }

    public String getValue() {
        return value;
    }

   /** public static Status forValue(String value) {
        for (final Status status : Status.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new RuntimeException("FieldValidatorKey with such string value doesn't exist");
    }**/
}