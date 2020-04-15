package hanq.groupware.co.kr.employee.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hanq.groupware.co.kr.employee.core.HanqRestController;
import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.service.EmployeeService;
import hanq.groupware.co.kr.employee.employee.service.FeignEmployeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@HanqRestController
public class EmployeeController {
	
	private final EmployeeService eployeeService;
	
	private final FeignEmployeeService feignEmployeeService;
	
	@GetMapping("/get/find/{employeeId}")
	public ResponseObject<Employee> getEmployee(@PathVariable Long employeeId) {
//		ResponseObject<Employee> employeInfo = eployeeService.getEmployeeInfo(employeeId);
		ResponseObject<Employee> employeInfo = eployeeService.getEmployeeInfo(employeeId);
		
		return employeInfo;
	}
	
	@GetMapping("/get/roles/{employeeId}")
	public ResponseObject<List<String>> getEmployeeRoles(@PathVariable String employeeId) {
		ResponseObject<List<String>> employeInfo = eployeeService.getEmployeeRoles(employeeId);
		
		return employeInfo;
	}
	
	@GetMapping("/get/find/business/{employeeId}/{businessNo}")
	public String getBusinessOfEmployee(@PathVariable Long employeeId,
			@PathVariable String businessNo) {
//		ResponseObject<String> businessInfo = eployeeService.getBusinessOfEmployeeInfo(employeeId, businessNo);
		String businessInfo = feignEmployeeService.getBusinessOfEmployeeInfo(employeeId, businessNo);
		
		return businessInfo; 
	}
}
