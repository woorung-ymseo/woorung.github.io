package hanq.groupware.co.kr.employee.employee.service;

import java.util.List;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;

public interface EmployeeService {

	/**
	 * ���� ���� ��ȸ
	 * 
	 * @param emplyeeId
	 * @return
	 */
	ResponseObject<Employee> getEmployeeInfo(Long emplyeeId);
	
	ResponseObject<String> getBusinessOfEmployeeInfo(Long emplyeeId, String businessNo);

	/**
	 * ���� ���� ��ȸ
	 * 
	 * @param employeeId
	 * @return
	 */
	ResponseObject<List<String>> getEmployeeRoles(String employeeId);
}
