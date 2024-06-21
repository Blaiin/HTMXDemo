package htmx.app.errors.exception;

import lombok.Getter;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class BusinessException extends Exception {
    private static final ResourceBundle RESOURCE_BUNDLE =
            ResourceBundle.getBundle("messages.errors.BusinessException", Locale.getDefault());
    private final Reason reason;
    private final String element;

    private BusinessException(BusinessExceptionBuilder builder) {
        super(builder.reason.getMessage());
        this.reason = builder.reason;
        this.element = builder.context;
    }

    public String getExceptionReasoning() {
        if(this.getErrorCode() == 1007) return reason.getMessage().formatted(element);
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
        INVALID_EMAIL(1006, "invalid.email"),
        ALREADY_IN_USE(1007, "already.in.use");

        private final int code;
        private final String message;

        Reason(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    public static class BusinessExceptionBuilder {
        private final Reason reason;
        private String context;

        public BusinessExceptionBuilder(Reason reason) {
            this.reason = reason;
        }

        public BusinessExceptionBuilder addProblematicElement(String element) {
            context = element;
            return this;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }
    }
}
