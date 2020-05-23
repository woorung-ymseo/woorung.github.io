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

	/**
	 * 회원상세정보 (Employee No)
	 *
	 * @param employeeNo
	 * @return
	 */
	@GetMapping("/get/{employeeNo}")
	public ResponseObject<Employee> getEmployee(@PathVariable Long employeeNo) {
		ResponseObject<Employee> employeInfo = eployeeService.getEmployeeInfo(employeeNo);
		
		return employeInfo;
	}

	/**
	 * 회원상세정보 (Employee Id)
	 *
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/get/id/{employeeId}")
	public ResponseObject<Employee> getEmployeeById(@PathVariable String employeeId) {
		ResponseObject<Employee> employeInfo = eployeeService.getEmployeeInfo(employeeId);

		return employeInfo;
	}

	/**
	 * 회원리스트
	 *
	 * @return
	 */
	@GetMapping("/get/employees")
	public ResponseObject<List<Employee>> getEmployeeList() {
		ResponseObject<List<Employee>> employeInfo = eployeeService.getEmployeeList();

		return employeInfo;
	}

	/**
	 * 권한 조회
	 * @param employeeId
	 * @return
	 */
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
