package hanq.groupware.co.kr.employee.core.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.core.exception.AuthenticationEntryPointException;
import hanq.groupware.co.kr.employee.employee.domain.Employee;

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
	
	@ExceptionHandler(value = {BadRequest.class, feign.FeignException.BadRequest.class})
	public ResponseObject<Object> handlerBadRequestException(IllegalArgumentException e) {
		return ResponseObject.builder()
				.status(HttpStatus.BAD_REQUEST)
				.resultCode("E001")
				.resultStatus("FAIL")
				.resultMessage(e.getMessage())
				.build();
	}
	
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
