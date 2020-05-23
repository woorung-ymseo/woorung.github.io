package hanq.groupware.co.kr.zuul.core.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hanq.groupware.co.kr.zuul.core.exception.Auth.AuthenticationEntryPointException;

/**
 * 예외 컨트롤러
 * 
 * @author user
 *
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {
	
	/**
	 * Security Auth Entry Point Error
	 */
	@GetMapping("/entryPoint")
	public void entryPointException() {
		throw new AuthenticationEntryPointException();
	}
}
