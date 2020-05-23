package hanq.groupware.co.kr.zuul.api.auth.sign.service;

import java.util.List;

import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignInParam;
import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignIn;
import hanq.groupware.co.kr.zuul.api.auth.sign.service.factory.FeignAuthServiceFallbackFactory;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;

@FeignClient(name = "employee", contextId = "feignAuth", fallbackFactory = FeignAuthServiceFallbackFactory.class)
public interface FeignAuthService {

	/**
	 * 로그인
	 * 
	 * @param signInParam
	 * @return
	 */
	@RequestMapping(value = "/api/employee/post/signin")
	ResponseObject<SignIn> postSignIn(SignInParam signInParam);

	/**
	 * 권한 가져오기
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value = "/api/employee/get/roles/{employeeId}")
	ResponseObject<List<String>> getEmployeeRoles(@PathVariable("employeeId") String employeeId);

	/**
	 * 회원가입
	 *
	 * @param employee
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/api/employee/post/signup")
	ResponseObject<String> postSignUp(Employee employee);
}
