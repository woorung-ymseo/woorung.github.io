package hanq.groupware.co.kr.employee.sign.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.inject.internal.Errors;

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
	
	@GetMapping("/signinTest")
	public String singin22() {
		return "ㅎㅇ";
	}
			

	@PostMapping("/signin")
	public ResponseObject<SignResDto> singin(@RequestBody @Valid SignReqDto signReqDto, Errors errors) 
	    throws UsernameNotFoundException {
	    ResponseObject<Employee> resSignEmployeeInfo = signService.employeeSignIn(signReqDto); 
	    
	    return ResponseObject.<SignResDto>builder()
	    		.body(SignResDto.builder()
	    				.employeeId(resSignEmployeeInfo.getBody().getEmployeeId())
	    				.build())
	    		.build();
	}
//	
//	
//	@PostMapping("/signin")
//	public Map<String, Object> singin(@RequestBody SignReqDto signReqDto) 
//			throws UsernameNotFoundException {
//		
//		if (signReqDto == null || StringUtils.isEmpty(signReqDto.getEmployeeId()) || StringUtils.isEmpty(signReqDto.getEmployeePassword())) {
//			throw new RuntimeException("잘못된 요청");
//		}
//		
//		ResponseObject<Employee> resEmployee = eployeeService.getEmployeeInfo(Long.parseLong("1")); 
//		
//		Employee employee = resEmployee.getBody();
//		
//		Map<String, Object> returnMap = new HashMap<>();
//		
//		System.out.println("########### employee.getAuthorityList() : " + employee.getAuthorityList().toString());
//		
//		returnMap.put("token", jwtTokenProvider.createToken(employee.getEmployeeId(), employee.getAuthorityList()));
//		
//		System.out.println("######## returnMap : " + returnMap.toString());
//		
//		return returnMap;
//	}
}
