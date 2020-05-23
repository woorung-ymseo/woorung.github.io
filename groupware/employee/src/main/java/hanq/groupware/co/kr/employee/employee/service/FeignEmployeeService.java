package hanq.groupware.co.kr.employee.employee.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hanq.groupware.co.kr.employee.employee.service.factory.FeignEmployeeServiceFallbackFactory;

@FeignClient(name = "business", fallbackFactory = FeignEmployeeServiceFallbackFactory.class) // eureka ���
//@FeignClient(name = "employee", url = "http://localhost:8081")
public interface FeignEmployeeService {

	@RequestMapping("/business/get/find/{businessNo}/{employeeId}")
	String getBusinessOfEmployeeInfo(@PathVariable("employeeId") Long employeeId, @PathVariable("businessNo") String businessNo);
//	ResponseObject<String> getBusinessOfEmployeeInfo(@PathVariable("employeeId") Long employeeId, @PathVariable("businessNo") String businessNo);
}
