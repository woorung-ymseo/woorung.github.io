package hanq.groupware.co.kr.employee.employee.service;

import java.util.List;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;

public interface EmployeeService {

	/**
	 * 회원 상세정보
	 *
	 * @param employeeNo
	 * @return
	 */
	ResponseObject<Employee> getEmployeeInfo(Long employeeNo);
	/**
	 * 회원 상세정보
	 *
	 * @param employeeId
	 * @return
	 */
	ResponseObject<Employee> getEmployeeInfo(String employeeId);
	
	/**
	 * 회원리스트
	 *
	 * @return
	 */
	ResponseObject<List<Employee>> getEmployeeList();


	ResponseObject<String> getBusinessOfEmployeeInfo(Long emplyeeId, String businessNo);

	/**
	 *
	 * @param employeeId
	 * @return
	 */
	ResponseObject<List<String>> getEmployeeRoles(String employeeId);

}
