package hanq.groupware.co.kr.employee.sign.service.impl;

import java.util.Optional;

import hanq.groupware.co.kr.employee.core.utils.ResponseObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.repository.EmployeeRepository;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;
import hanq.groupware.co.kr.employee.sign.dto.SignResDto;
import hanq.groupware.co.kr.employee.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 계정 관련 Service
 */
@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {
	
	private final PasswordEncoder passwordEncoder;

	private final EmployeeRepository employeeRepository;

	private final ResponseObjectUtils responseObjectUtils;
	
	@Override
	public ResponseObject<Employee> employeeSignIn(SignReqDto signReqDto) {
		Optional<Employee> employeeInfo = employeeRepository.findByEmployeeId(signReqDto.getEmployeeId());
		
		if (employeeInfo.isPresent()) {
			System.out.println("### employeeInfo  :" + employeeInfo.get().getEmployeePassword());

			if (passwordEncoder.matches(signReqDto.getEmployeePassword(), employeeInfo.get().getEmployeePassword())) {
			
				return ResponseObject.<Employee>builder()
						.body(employeeInfo.get())
						.build();
			} else {
				throw new IllegalArgumentException("Invalid Password");
			}
		} else {
			throw new IllegalArgumentException("Employee Not Found");
		}
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public ResponseObject<String> employeeSignUp(Employee employee) {
		String employeeId = employee.getEmployeeId();

		Optional<Employee> employeeInfo = employeeRepository.findByEmployeeId(employeeId);

		// ID 중복
		if (employeeInfo.isPresent()) {
			StringBuffer sBuffer = new StringBuffer();

			sBuffer.append("(")
				   .append(employeeId)
				   .append(") 중복된 ID");

			return responseObjectUtils.responseForErrors(HttpStatus.BAD_REQUEST, sBuffer.toString());
		}

		String passWord = employee.getPassword();

		// Password Encoding
		employee.setEmployeePassword(passwordEncoder.encode(passWord));

		employeeRepository.save(employee);

		return responseObjectUtils.responseForOk("회원가입 완료");
	}
}
