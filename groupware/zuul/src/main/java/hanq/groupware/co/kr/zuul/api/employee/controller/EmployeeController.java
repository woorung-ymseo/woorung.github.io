package hanq.groupware.co.kr.zuul.api.employee.controller;

import hanq.groupware.co.kr.zuul.api.auth.sign.domain.SignIn;
import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.api.employee.service.FeignEmployeeService;
import hanq.groupware.co.kr.zuul.core.annotation.HanqRestController;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "직원")
@RequiredArgsConstructor
@HanqRestController
public class EmployeeController {

    private final ResponseObjectUtils responseObjectUtils;

    private final FeignEmployeeService feignEmployeeService;

    @ApiOperation(value = "회원리스트조회")
    @GetMapping("/employees")
    public ResponseObject<Employee> employeeList() {
        ResponseObject<List<Employee>> employee = feignEmployeeService.getEmployeeList();

        return responseObjectUtils.responseForOk(employee);
    }

    @ApiOperation(value = "회원정보조회 (Employee No)")
    @GetMapping("/employee/{employeeNo}")
    public ResponseObject<Employee> employeeInfo(@ApiParam(value = "직원번호", required = true) @PathVariable Long employeeNo) {
        System.out.println("##### employeeNo : " + employeeNo);

        ResponseObject<Employee> employee = feignEmployeeService.getEmployeeInfo(employeeNo);

        return responseObjectUtils.responseForOk(employee);
    }

    @ApiOperation(value = "회원정보조회 (Employee Id)")
    @GetMapping("/employee/id/{employeeId}")
    public ResponseObject<SignIn> employeeInfoById(@ApiParam(value = "직원ID", required = true) @PathVariable String employeeId) {
        System.out.println("##### employeeID : " + employeeId);

        ResponseObject<Employee> employee = feignEmployeeService.getEmployeeInfo(employeeId);

        return responseObjectUtils.responseForOk(employee);
    }

    @Transactional
//    @PreAuthorize("isAuthenticated() and (( #Employee.employeeNo == principal.employeeNo ) or hasRole('ROLE_ADMIN'))")
    @ApiOperation(value = "회원정보수정")
    @PostMapping("/employee/patch")
    public ResponseObject<Employee> signUp(@RequestBody @Valid Employee employee,
                                         @ApiIgnore Errors errors) {

        if (errors.hasErrors()) {
            return responseObjectUtils.responseForErrors(errors);
        }

        ResponseObject<Employee> resPatchEmployee = feignEmployeeService.patchEmployeeInfo(employee);

        return resPatchEmployee;
    }
}
