package hanq.groupware.co.kr.zuul.api.employee.validation;

import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.core.enumeration.code.ResponseCodeEnum;
import hanq.groupware.co.kr.zuul.core.utils.ResponseObjectUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class EmployeeValidatior {

    private final ResponseObjectUtils responseObjectUtils;

    @Value("${regx.employee.password}")
    String regxPassword;

    /**
     * 회원가입시 validation 체크
     * @param employee
     * @param errors
     * @return
     */
    public boolean employeeSignUpValidator(Employee employee, Errors errors) {
        String employeePassword = employee.getEmployeePassword();

        // 비밀번호가 없는 경우
        if (StringUtils.isEmpty(employeePassword)) {
            ResponseCodeEnum.responseError("employeePassword", ResponseCodeEnum.EMPLOYEE_MISSING_PASSWORD, errors);
        } else if (employeePassword.length() < 8 || employeePassword.length() > 20) {
            ResponseCodeEnum.responseError("employeePassword", ResponseCodeEnum.EMPLOYEE_INVALID_LENGTH_PASSWORD, errors);
        } else if (!Pattern.matches(regxPassword, employeePassword)) {
            ResponseCodeEnum.responseError("employeePassword", ResponseCodeEnum.EMPLOYEE_INVALID_PATTERN_PASSWORD, errors);
        } else {
            return true;
        }
        
        return false;
    }

    /**
     * 회원정보 수정시 validation 체크
     *
     * @param employee
     * @param errors
     * @return
     */
    public boolean employeeModifyValidator(Employee employee, Errors errors) {

        return true;
    }
}
