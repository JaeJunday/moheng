package moheng.global.error;

import moheng.auth.exception.EmptyBearerHeaderException;
import moheng.auth.exception.InvalidOAuthServiceException;
import moheng.auth.exception.InvalidTokenFormatException;
import moheng.global.error.dto.ExceptionResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(InvalidOAuthServiceException.class)
    public ResponseEntity<ExceptionResponse> handleOAuthException(final RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler({
            EmptyBearerHeaderException.class,
            InvalidTokenFormatException.class})
    public ResponseEntity<ExceptionResponse> handleUnAuthorizedException(RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse errorResponse = new ExceptionResponse(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        logger.error(e.getMessage(), e);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOverflowException(final Exception e) {
        logger.error(e.getMessage(), e);

        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse("서버에 예기치 못한 오류가 발생했습니다. 관리자에게 문의하세요."));
    }
}
