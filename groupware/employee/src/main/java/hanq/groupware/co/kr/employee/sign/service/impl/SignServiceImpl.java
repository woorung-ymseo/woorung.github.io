package hanq.groupware.co.kr.employee.sign.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.repository.EmployeeRepository;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;
import hanq.groupware.co.kr.employee.sign.dto.SignResDto;
import hanq.groupware.co.kr.employee.sign.service.SignService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SignServiceImpl implements SignService {
	
	private final PasswordEncoder passwordEncoder;

	private final EmployeeRepository employeeRepository;
	
	@Override
	public ResponseObject<Employee> employeeSignIn(SignReqDto signReqDto) {
		System.out.println("#### signReqDto  : "  + signReqDto.toString());
		
		Optional<Employee> employeeInfo = employeeRepository.findByEmployeeId(signReqDto.getEmployeeId());

		
//		Optional<Employee> employeeSign = employeeRepository.findByEmployeeIdAndEmployeePassword(signReqDto.getEmployeeId(), signReqDto.getEmployeePassword());
		
		if (employeeInfo.isPresent()) {
			System.out.println("### employeeInfo  :" + employeeInfo.get().getEmployeePassword());

			if (passwordEncoder.matches(signReqDto.getEmployeePassword(), employeeInfo.get().getEmployeePassword())) {
			
				return ResponseObject.<Employee>builder()
						.body(employeeInfo.get())
						.build();
			} else {
				throw new IllegalArgumentException("Invalid Password (로그인 실패)");
			}
		} else {
			throw new IllegalArgumentException("Employee Not Found (로그인 실패)");
		}
	}
}
