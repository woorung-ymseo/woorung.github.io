package hanq.groupware.co.kr.zuul.employee.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hanq.groupware.co.kr.zuul.auth.service.factory.FeignAuthServiceFallbackFactory;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;

//@FeignClient(name = "employee", fallbackFactory = FeignAuthServiceFallbackFactory.class)
public interface FeignEmployeeService {

	/**
	 * 직원 권한 가져오기
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value = "/api/employee/get/roles/{employeeId}")
	public ResponseObject<List<String>> getEmployeeRoles(@PathVariable("employeeId") String employeeId);
}
