package hanq.groupware.co.kr.employee.sign.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hanq.groupware.co.kr.employee.core.HanqRestController;
import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.core.security.JwtTokenProvider;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.service.EmployeeService;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;
import hanq.groupware.co.kr.employee.sign.dto.SignResDto;
import hanq.groupware.co.kr.employee.sign.service.SignService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@HanqRestController
public class SignController {
	
	private final JwtTokenProvider jwtTokenProvider;

	private final SignService signService;

	/**
	 * 로그인
	 *
	 * @param signReqDto
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@PostMapping("/post/signin")
	public ResponseObject<SignResDto> singIn(@RequestBody @Valid SignReqDto signReqDto)
	    throws UsernameNotFoundException {
	    ResponseObject<Employee> resSignEmployeeInfo = signService.employeeSignIn(signReqDto); 
	    
	    return ResponseObject.<SignResDto>builder()
	    		.body(SignResDto.builder()
	    				.employeeId(resSignEmployeeInfo.getBody().getEmployeeId())
	    				.build())
	    		.build();
	}


	@PostMapping("/post/signup")
	public ResponseObject<String> singUp(@RequestBody @Valid Employee employee) {
	    ResponseObject<String> resSignEmployeeInfo = signService.employeeSignUp(employee);

	    return resSignEmployeeInfo;
	}
}
