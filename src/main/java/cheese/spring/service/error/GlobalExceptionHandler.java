package cheese.spring.service.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final int status = 400;
        final ErrorResponse response = ErrorResponse.of("입력값이 올바르지 않습니다.", status, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindExceptionException(final BindException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final int status = 400;
        final ErrorResponse response = ErrorResponse.of("입력값이 올바르지 않습니다.", status, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(final Exception e) {
        log.error("handleMethodArgumentNotValidException", e);
        final int status = 500;
        final ErrorResponse response = ErrorResponse.of("예러가 발생했습니다.", status);
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }
}
