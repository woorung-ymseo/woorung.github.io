package hanq.groupware.co.kr.zuul.api.employee.service;

import hanq.groupware.co.kr.zuul.api.auth.sign.service.factory.FeignAuthServiceFallbackFactory;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.api.employee.factory.FeignEmployeeServiceFalllbackFactory;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "employee", contextId = "feignEmployee", fallbackFactory = FeignEmployeeServiceFalllbackFactory.class)
public interface FeignEmployeeService {

    /**
     * 회원정보 조회 (Employee No)
     *
     * @param employeeNo
     * @return
     */
    @RequestMapping(value = "/api/employee/get/{employeeNo}")
    ResponseObject<Employee> getEmployeeInfo(@PathVariable("employeeNo") Long employeeNo);

    /**
     * 회원정보 조회 (Employee Id)
     *
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/api/employee/get/id/{employeeId}")
    ResponseObject<Employee> getEmployeeInfo(@PathVariable("employeeId") String employeeId);

    /**
     * 회원리스트 조회
     *
     * @return
     */
    @RequestMapping(value = "/api/employee/get/employees")
    ResponseObject<List<Employee>> getEmployeeList();

    /**
     * 회원정보 수정
     * @param employee
     * @return
     */
    @RequestMapping(value = "/api/employee/patch")
    ResponseObject<Employee> patchEmployeeInfo(Employee employee);
}
