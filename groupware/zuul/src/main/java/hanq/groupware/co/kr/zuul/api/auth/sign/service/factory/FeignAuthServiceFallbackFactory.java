package hanq.groupware.co.kr.zuul.api.auth.sign.service.factory;

import java.util.List;

import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignInParam;
import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignIn;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import hanq.groupware.co.kr.zuul.api.auth.sign.service.FeignAuthService;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;

@RequiredArgsConstructor
@Component
public class FeignAuthServiceFallbackFactory implements FallbackFactory<FeignAuthService> {
	
	private ResponseObjectUtils responseObjectUtils;

	@Override
	public FeignAuthService create(Throwable cause) {
		return new FeignAuthService() {

			@Override
			public ResponseObject<SignIn> postSignIn(SignInParam signInParam) {
				// TODO Auto-generated method stub
				return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
			}

			@Override
			public ResponseObject<List<String>> getEmployeeRoles(String employeeId) {
				// TODO Auto-generated method stub
				return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
			}

			@Override
			public ResponseObject<String> postSignUp(Employee employee) {
				return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
			}
		};
	}
}
