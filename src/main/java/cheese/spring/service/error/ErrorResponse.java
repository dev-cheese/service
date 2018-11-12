package cheese.spring.service.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;


    private ErrorResponse(String message, int status, List<FieldError> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    private ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.errors = new ArrayList<>();
    }


    public static ErrorResponse of(String message, int status, BindingResult bindingResult) {
        return new ErrorResponse(message, status, buildFieldError(bindingResult));
    }

    public static ErrorResponse of(String message, int status) {
        return new ErrorResponse(message, status);
    }

    private static List<FieldError> buildFieldError(BindingResult bindingResult) {
        final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.parallelStream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value(error.getRejectedValue().toString())
                        .build())
                .collect(Collectors.toList());
    }

    @Getter
    static class FieldError {
        private String field;
        private String value;
        private String reason;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}
