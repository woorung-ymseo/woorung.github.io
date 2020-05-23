package hanq.groupware.co.kr.zuul.core.aop.error;

import javax.servlet.http.HttpServletRequest;

import feign.FeignException;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.exception.Auth.AuthenticationEntryPointException;

/**
 * Controller Error Handler
 * 
 * @author user
 *
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class RestAdvice {

	private final ResponseObjectUtils responseObjectUtils;

	/**
	 * Runtime
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseObject<Object> handlerRuntimeException(RuntimeException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	/**
	 * NullPoint
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseObject<Object> handlerNullException(NullPointerException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	/**
	 * Feign
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FeignException.class)
	public ResponseObject<Object> handlerFeignException(FeignException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	/**
	 * Feign Internal server
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FeignException.InternalServerError.class)
	public ResponseObject<Object> handlerException(FeignException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	/**
	 * Illegal Argument
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseObject<Object> handlerIllegalException(IllegalArgumentException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.PAYMENT_REQUIRED, e.getMessage());
	}

	/**
	 * Authentication
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationEntryPointException.class)
	public ResponseObject<Object> authenticationEntryPointException(HttpServletRequest request, AuthenticationEntryPointException e) {
		return responseObjectUtils.responseForErrors(HttpStatus.FORBIDDEN, e.getMessage());
	}
}
