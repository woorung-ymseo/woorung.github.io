package hanq.groupware.co.kr.employee.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import hanq.groupware.co.kr.employee.core.entity.ResponseObject;
import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.repository.EmployeeRepository;
import hanq.groupware.co.kr.employee.employee.service.EmployeeService;
import hanq.groupware.co.kr.employee.employee.service.FeignEmployeeService;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;
import hanq.groupware.co.kr.employee.roles.repository.RolesRepository;
import hanq.groupware.co.kr.employee.sign.dto.SignReqDto;
import hanq.groupware.co.kr.employee.sign.dto.SignResDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final RestTemplate restTemplate;
	
	private final EmployeeRepository employeeRepository;
	
	private static final String URL = "http://business/business/get/find/";
	
	@Override
	public ResponseObject<Employee> getEmployeeInfo(Long employeeNo) {
		Optional<Employee> employee = employeeRepository.findById(employeeNo);

		return ResponseObject.<Employee>builder()
				.body(employee.get())
				.build();
	}

	@Override
	public ResponseObject<Employee> getEmployeeInfo(String employeeId) {
		Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);

		return ResponseObject.<Employee>builder()
				.body(employee.get())
				.build();
	}

	@Override
	public ResponseObject<List<Employee>> getEmployeeList() {
		List<Employee> employeeList = employeeRepository.findAll();

		return ResponseObject.<List<Employee>>builder()
				.body(employeeList)
				.build();
	}

	/**
	 */
	@Override
	public ResponseObject<List<String>> getEmployeeRoles(String employeeId) {
		Optional<Employee> employee = employeeRepository.findByEmployeeId(employeeId);
		
		if (employee.isPresent()) {

			return ResponseObject.<List<String>>builder()
					.body(employee.get().getAuthorityList())
					.build();
		}

		return null;
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public ResponseObject<Employee> patchEmployeeInfo(Employee employee) {
		System.out.println("## employee : " + employee.toString());

		employeeRepository.save(employee);
		return null;
	}

	@Override
	@HystrixCommand(
			groupKey = "employee", 
	commandKey = "bussinessOfEmployeeInfo", fallbackMethod = "getBusinessOfEmployeeInfoFallback")
//			commandProperties = {
//				@HystrixProperty(name = "excution.isolation.thread.timeoutInMilliseconds", value = "500")
//			}, 
//			threadPoolProperties = {
//				@HystrixProperty(name = "coreSize", value = "30"), // default 10
//				@HystrixProperty(name = "maxQueueSize", value = "101"),	
//				@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),	
//				@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),	
//				@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),	
//				@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")	
//			})
	public ResponseObject<String> getBusinessOfEmployeeInfo(Long emplyeeId, String businessNo) {
		String response = restTemplate.getForObject(URL + businessNo + "/" + emplyeeId, String.class);

		return ResponseObject.<String>builder()
				.body(response)
				.build();
	} 
	
	public ResponseObject<String> getBusinessOfEmployeeInfoFallback(Long emplyeeId, String businessNo,
			Throwable t) {

		return ResponseObject.<String>builder()
				.body("[ emplyeeId = " + emplyeeId + " | businessNo = " + businessNo +" ]")
				.build();
	}


	
}
