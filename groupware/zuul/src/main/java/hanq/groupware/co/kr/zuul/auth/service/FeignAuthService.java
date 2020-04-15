package hanq.groupware.co.kr.zuul.auth.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hanq.groupware.co.kr.zuul.auth.dto.SignReqDto;
import hanq.groupware.co.kr.zuul.auth.dto.SignResDto;
import hanq.groupware.co.kr.zuul.auth.service.factory.FeignAuthServiceFallbackFactory;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.employee.domain.Employee;

@FeignClient(name = "employee", fallbackFactory = FeignAuthServiceFallbackFactory.class)
//@FeignClient(name = "employee", url = "http://localhost:8080")
public interface FeignAuthService {

	/**
	 * 로그인
	 * 
	 * @param signReqDto
	 * @return
	 */
	@RequestMapping(value = "/api/employee/signin")
	public ResponseObject<SignResDto> signIn(SignReqDto signReqDto);
	/**
	 * 로그인
	 * 
	 * @param signReqDto
	 * @return
	 */
	@RequestMapping(value = "/api/employee/signinTest")
	public String signIn22();
	
	/**
	 * 직원 권한 가져오기
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value = "/api/employee/get/roles/{employeeId}")
	public ResponseObject<List<String>> getEmployeeRoles(@PathVariable("employeeId") String employeeId);
}
