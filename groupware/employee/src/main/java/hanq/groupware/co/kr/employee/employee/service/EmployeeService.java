package hanq.groupware.co.kr.employee.employee.service;

import java.util.List;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;

public interface EmployeeService {

	/**
	 * 직원 정보 조회
	 * 
	 * @param emplyeeId
	 * @return
	 */
	ResponseObject<Employee> getEmployeeInfo(Long emplyeeId);
	
	ResponseObject<String> getBusinessOfEmployeeInfo(Long emplyeeId, String businessNo);

	/**
	 * 직원 권한 조회
	 * 
	 * @param employeeId
	 * @return
	 */
	ResponseObject<List<String>> getEmployeeRoles(String employeeId);
}
