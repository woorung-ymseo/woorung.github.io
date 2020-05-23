package hanq.groupware.co.kr.employee.employee.service.factory;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import hanq.groupware.co.kr.employee.employee.service.FeignEmployeeService;

@Component
public class FeignEmployeeServiceFallbackFactory implements FallbackFactory<FeignEmployeeService> {

	@Override
	public FeignEmployeeService create(Throwable cause) {
		System.out.println("#### cause : " + cause);
//		return (employeeId, businessNo) -> ("");
		return new FeignEmployeeService() {
			@Override
			public String getBusinessOfEmployeeInfo(Long employeeId, String businessNo) {
				// TODO Auto-generated method stub
				return "gd?";
			}
		};
	}
}
