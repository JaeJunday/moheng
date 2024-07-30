package moheng.global.error;

import moheng.auth.exception.*;
import moheng.global.error.dto.ExceptionResponse;
import moheng.member.exception.*;
import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler({
            BadRequestException.class,
            NoExistOAuthClientException.class,
            NoExistOAuthClientException.class,
            InvalidBirthdayException.class,
            InvalidEmailFormatException.class,
            InvalidGenderFormatException.class,
            InvalidNicknameFormatException.class,
            NoExistMemberTokenException.class,
            NoExistSocialTypeException.class
    })
    public ResponseEntity<ExceptionResponse> handleInvalidData(final RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }


    @ExceptionHandler(InvalidOAuthServiceException.class)
    public ResponseEntity<ExceptionResponse> handleOAuthException(final RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler({
            EmptyBearerHeaderException.class,
            InvalidTokenFormatException.class,
            InvalidTokenException.class})
    public ResponseEntity<ExceptionResponse> handleUnAuthorizedException(final RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse errorResponse = new ExceptionResponse(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestBody() {
        ExceptionResponse exceptionResponse = new ExceptionResponse("잘못된 Request Body 형식 입니다.");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOverflowException(final Exception e) {
        logger.error(e.getMessage(), e);

        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse("서버에 예기치 못한 오류가 발생했습니다. 관리자에게 문의하세요."));
    }
}
