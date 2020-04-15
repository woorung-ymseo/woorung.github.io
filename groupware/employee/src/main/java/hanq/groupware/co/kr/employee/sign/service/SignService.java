package hanq.groupware.co.kr.employee.sign.service;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;

public interface SignService {
	
	/**
	 * ·Î±×ÀÎ
	 * 
	 * @param signReqDto
	 * @return
	 */
	ResponseObject<Employee> employeeSignIn(SignReqDto signReqDto);
}
