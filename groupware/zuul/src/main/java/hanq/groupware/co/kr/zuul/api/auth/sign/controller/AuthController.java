package hanq.groupware.co.kr.zuul.api.auth.sign.controller;

import java.util.List;

import javax.validation.Valid;

import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignInParam;
import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignIn;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.api.employee.validation.EmployeeValidatior;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import hanq.groupware.co.kr.zuul.core.utils.WebCookieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hanq.groupware.co.kr.zuul.api.auth.sign.service.FeignAuthService;
import hanq.groupware.co.kr.zuul.core.annotation.HanqRestController;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "권한")
@RequiredArgsConstructor
@HanqRestController
public class AuthController {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final FeignAuthService feignAuthService;

	private final ResponseObjectUtils responseObjectUtils;

	private final EmployeeValidatior employeeValidatior;

	@ApiOperation(value = "로그인")
	@PostMapping("/auth/signin")
	public ResponseObject<SignIn> signIn(@RequestBody @Valid SignInParam signReqDto) throws UsernameNotFoundException {
		ResponseObject<SignIn> signIn = feignAuthService.postSignIn(signReqDto);
		
		if (signIn.getBody() != null) {
			String employeeId = signReqDto.getEmployeeId();

			ResponseObject<List<String>> roles = feignAuthService.getEmployeeRoles(employeeId);
			
			// ACCESS 토큰 생성
			String token = jwtTokenProvider.createToken(employeeId, roles.getBody());

			signIn.getBody().setToken(token);

			WebCookieUtils.addCookie("login-user", "ㅎㅇㅎㅇ");
		}

		return signIn;
	}

	@Transactional
	@ApiOperation(value = "회원가입")
	@PostMapping("/auth/signup")
	public ResponseObject<String> signUp(@RequestBody @Valid Employee employee,
										 @ApiIgnore Errors errors) {

		employeeValidatior.employeeSignUpValidator(employee, errors);

		if (errors.hasErrors()) {
			return responseObjectUtils.responseForErrors(errors);
		}

//		ResponseObject<String> resSignUp = feignAuthService.postSignUp(employee);

		return null;
//		return resSignUp;
	}
}
