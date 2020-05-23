package hanq.groupware.co.kr.employee.sign.service;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;

/**
 * 회원 계정 관련 Service
 */
public interface SignService {
	
	/**
	 * 로그인
	 *
	 * @param signReqDto
	 * @return
	 */
	ResponseObject<Employee> employeeSignIn(SignReqDto signReqDto);

	/**
	 * 회원가입
	 *
	 * @param employee
	 * @return
	 */
	ResponseObject<String> employeeSignUp(Employee employee);
}
