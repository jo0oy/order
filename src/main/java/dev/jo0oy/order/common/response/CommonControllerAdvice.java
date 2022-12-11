package dev.jo0oy.order.common.response;

import dev.jo0oy.order.common.exception.BaseException;
import dev.jo0oy.order.common.exception.InvalidParamException;
import dev.jo0oy.order.common.interceptor.CommonHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.MDC;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    private static final List<ErrorCode> SPECIFIC_ALERT_ERROR_CODE_LIST = new ArrayList<>();

    // http status = 500 && result = fail
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandle(Exception ex) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HTTP_HEADER_REQUEST_UUID_KEY);
        log.error("[event-id] = {}", eventId, ex);

        return ResponseEntity.internalServerError()
                .body(ErrorResponse.fail(ex.getMessage(), ErrorCode.COMMON_SYSTEM_ERROR));
    }

    // http status = 200 && result = fail
    // 시스템에는 오류 없음, 비즈니스 로직에서 에러 발생.
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<?> baseExceptionHandle(BaseException ex) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HTTP_HEADER_REQUEST_UUID_KEY);

        if (SPECIFIC_ALERT_ERROR_CODE_LIST.contains(ex.getErrorCode())) {
            log.error("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(ex),
                    NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
        } else {
            log.warn("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(ex),
                    NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
        }

        return ResponseEntity.ok()
                .body(ErrorResponse.fail(ex.getMessage(), ex.getErrorCode()));
    }

    // 예상치 못한 exception 발생 && skip 가능한 경우
    // ex) ClientAbortException
    @ExceptionHandler(value = {ClientAbortException.class})
    public ResponseEntity<?> skipExceptionHandle(Exception ex) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HTTP_HEADER_REQUEST_UUID_KEY);
        log.warn("[SkipException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(ex),
                NestedExceptionUtils.getMostSpecificCause(ex).getMessage());

        return ResponseEntity.ok()
                .body(ErrorResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR));
    }

    // http status = 400 && result = fail
    @ExceptionHandler(value = {InvalidParamException.class})
    public ResponseEntity<?> invalidParamExceptionHandle(InvalidParamException ex) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HTTP_HEADER_REQUEST_UUID_KEY);
        log.error("[invalidParamException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(ex),
                NestedExceptionUtils.getMostSpecificCause(ex).getMessage());

        return ResponseEntity.badRequest()
                .body(ErrorResponse.fail(ex.getMessage(), ex.getErrorCode()));
    }

    // http status = 400 && result = fail
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> ArgNotValidExceptionHandle(MethodArgumentNotValidException ex) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HTTP_HEADER_REQUEST_UUID_KEY);
        log.error("[ArgNotValidException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(ex),
                NestedExceptionUtils.getMostSpecificCause(ex).getMessage());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        if (fieldErrors.size() > 0) {
            return ResponseEntity.badRequest()
                    .body(makeValidationErrorResponse(ex));
        } else {
            return ResponseEntity.badRequest()
                    .body(ErrorResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER));

        }
    }

    // validation error 정보 리스트 생성하여 담은 ErrorResponse 객체 반환
    private ErrorResponse<?> makeValidationErrorResponse(BindException ex) {

        List<ErrorResponse.ValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER, errors);
    }
}
