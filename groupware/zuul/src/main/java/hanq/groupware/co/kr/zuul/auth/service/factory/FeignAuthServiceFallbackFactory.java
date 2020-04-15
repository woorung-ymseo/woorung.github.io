package hanq.groupware.co.kr.zuul.auth.service.factory;

import java.util.List;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import hanq.groupware.co.kr.zuul.auth.dto.SignReqDto;
import hanq.groupware.co.kr.zuul.auth.dto.SignResDto;
import hanq.groupware.co.kr.zuul.auth.service.FeignAuthService;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.employee.domain.Employee;

@Component
public class FeignAuthServiceFallbackFactory implements FallbackFactory<FeignAuthService> {

	@Override
	public FeignAuthService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new FeignAuthService() {
			
			@Override
			public ResponseObject<SignResDto> signIn(SignReqDto signReqDto) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String signIn22() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ResponseObject<List<String>> getEmployeeRoles(String employeeId) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
