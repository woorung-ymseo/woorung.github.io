package hanq.groupware.co.kr.zuul.api.employee.factory;

import feign.hystrix.FallbackFactory;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.api.employee.service.FeignEmployeeService;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FeignEmployeeServiceFalllbackFactory implements FallbackFactory<FeignEmployeeService> {

    private final ResponseObjectUtils responseObjectUtils;

    @Override
    public FeignEmployeeService create(Throwable cause) {

        return new FeignEmployeeService() {
            @Override
            public ResponseObject<Employee> getEmployeeInfo(Long employeeNo) {
                return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
            }

            @Override
            public ResponseObject<Employee> getEmployeeInfo(String employeeId) {
                return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
            }

            @Override
            public ResponseObject<List<Employee>> getEmployeeList() {
                return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
            }

            @Override
            public ResponseObject<Employee> patchEmployeeInfo(Employee employee) {
                return responseObjectUtils.responseForErrors(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
            }
        };
    }
}
