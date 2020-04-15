package hanq.groupware.co.kr.employee.core.exception;

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
