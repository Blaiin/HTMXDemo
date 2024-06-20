package htmx.app.errors.exception;

import lombok.Getter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Getter
public class BusinessException extends Exception {
    private static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle("messages.errors.BusinessException", Locale.getDefault());
    private final Reason reason;
    private final Map<String, Object> context;

    private BusinessException(BusinessExceptionBuilder builder) {
        super(builder.reason.getMessage());
        this.reason = builder.reason;
        this.context = builder.context;
    }

    public String getExceptionReasoning() {
        return reason.getMessage();
    }

    public int getErrorCode() {
        return reason.getCode();
    }


    @Getter
    public enum Reason {
        USER_NOT_FOUND(1001, "user.not.found"),
        USER_ALREADY_EXISTS(1002, "user.already.exists"),
        INVALID_CREDENTIALS(1003, "invalid.credentials"),
        INVALID_USER(1004, "invalid.user"),
        INVALID_PASSWORD(1005, "invalid.password"),
        INVALID_EMAIL(1006, "invalid.email");

        private final int code;
        private final String message;

        Reason(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    public static class BusinessExceptionBuilder {
        private final Reason reason;
        private final Map<String, Object> context = new HashMap<>();

        public BusinessExceptionBuilder(Reason reason) {
            this.reason = reason;
        }

        public BusinessExceptionBuilder addContext(String key, Object value) {
            context.put(key, value);
            return this;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }
    }
}
