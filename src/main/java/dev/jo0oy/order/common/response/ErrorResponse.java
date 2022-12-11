package dev.jo0oy.order.common.response;

import lombok.*;
import org.springframework.validation.FieldError;

import java.time.ZonedDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {

    private boolean success;
    private ZonedDateTime responseTime;
    private String message;
    private ErrorCode errorCode;
    private T errors;

    public static<T> ErrorResponse<T> fail(final ErrorCode errorCode) {
        return fail(errorCode.getErrorMsg(), errorCode);
    }

    public static<T> ErrorResponse<T> fail(final ErrorCode errorCode, final T errors) {
        return fail(errorCode.getErrorMsg(), errorCode, errors);
    }

    public static<T> ErrorResponse<T> fail(final String message, final ErrorCode errorCode) {
        return fail(ZonedDateTime.now(), message, errorCode, null);
    }

    public static<T> ErrorResponse<T> fail(final String message, final ErrorCode errorCode, final T errors) {
        return fail(ZonedDateTime.now(), message, errorCode, errors);
    }

    public static<T> ErrorResponse<T> fail(final ZonedDateTime responseTime,
                                     final String message, final ErrorCode errorCode, final T errors) {
        return ErrorResponse.<T>builder()
                .success(false)
                .responseTime(responseTime)
                .message(message)
                .errorCode(errorCode)
                .errors(errors)
                .build();
    }

    @Builder
    @Getter
    @RequiredArgsConstructor
    public static class ValidationError {
       private final String field;
       private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
