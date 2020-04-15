package hanq.groupware.co.kr.zuul.core.exception.Auth;

/**
 * Security Auth Entry Point Exception
 * 
 * @author user
 *
 */
public class AuthenticationEntryPointException extends RuntimeException {

	public AuthenticationEntryPointException() {
		super();
	}

	public AuthenticationEntryPointException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationEntryPointException(String message) {
		super(message);
	}
}
