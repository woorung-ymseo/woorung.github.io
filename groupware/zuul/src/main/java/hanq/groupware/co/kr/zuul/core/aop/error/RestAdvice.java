package hanq.groupware.co.kr.zuul.core.aop.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.exception.Auth.AuthenticationEntryPointException;

/**
 * Controller 에러 시 Error Handler
 * 
 * @author user
 *
 */
@RestControllerAdvice
public class RestAdvice {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseObject<Object> handlerException(IllegalArgumentException e) {
		return ResponseObject.builder()
				.status(HttpStatus.PAYMENT_REQUIRED)
				.resultCode("E001")
				.resultStatus("FAIL")
				.resultMessage(e.getMessage())
				.build();
	}
	
	@ExceptionHandler(value = {HttpClientErrorException.class})
	public ResponseObject<Object> handlerBadRequestException(HttpClientErrorException e) {
		return ResponseObject.builder()
				.status(HttpStatus.BAD_REQUEST)
				.resultCode("E001")
				.resultStatus("FAIL")
				.resultMessage(e.getMessage())
				.build();
	}
	
	/**
	 * 권한 Error Handler
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationEntryPointException.class)
	public ResponseObject<Object> authenticationEntryPointException(HttpServletRequest request, AuthenticationEntryPointException e) {
		return ResponseObject.builder()
				.status(HttpStatus.FORBIDDEN)
				.resultCode("E002")
				.resultStatus("FAIL")
				.resultMessage("Invalid Token")
				.build();
	}
}
