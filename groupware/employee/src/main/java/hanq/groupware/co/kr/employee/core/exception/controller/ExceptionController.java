package hanq.groupware.co.kr.employee.core.exception.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.core.exception.AuthenticationEntryPointException;
import hanq.groupware.co.kr.employee.employee.domain.Employee;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
	
	@GetMapping("/entryPoint")
	public void entryPointException() {
		throw new AuthenticationEntryPointException();
	}
}
