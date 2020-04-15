package hanq.groupware.co.kr.zuul.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hanq.groupware.co.kr.zuul.auth.dto.SignReqDto;
import hanq.groupware.co.kr.zuul.auth.dto.SignResDto;
import hanq.groupware.co.kr.zuul.auth.service.FeignAuthService;
import hanq.groupware.co.kr.zuul.core.annotation.HanqRestController;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import hanq.groupware.co.kr.zuul.employee.domain.Employee;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@HanqRestController
public class AuthController {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final FeignAuthService feignAuthService;

	@RequestMapping("/auth/signin")
	public ResponseObject<SignResDto> signIn(@RequestBody @Valid SignReqDto signReqDto) throws UsernameNotFoundException {
		String employeeId = signReqDto.getEmployeeId();
		
		System.out.println("### signReqDto : " + signReqDto.toString());
		
		ResponseObject<SignResDto> resSign = feignAuthService.signIn(signReqDto);
		
		if (resSign.getBody() != null) {
			ResponseObject<List<String>> roles = feignAuthService.getEmployeeRoles(employeeId);
			
			String token = jwtTokenProvider.createToken(employeeId, roles.getBody());
			
			resSign.getBody().setToken(token);
			
			System.out.println("######## roles : " + roles.toString());
		}
		
		System.out.println("######## res : " + resSign.toString());
		
		return resSign;
	}
	
	@RequestMapping("/auth/signin22")
	public String signIn() throws UsernameNotFoundException {
		System.out.println("######## res : " + feignAuthService.signIn22());
		
		return "Gd";
	}
}
