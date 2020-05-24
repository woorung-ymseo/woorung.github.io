package hanq.groupware.co.kr.zuul.core.enumeration.code;

import org.springframework.validation.Errors;

public enum ResponseCodeEnum {

    EMPLOYEE_MISSING_PASSWORD("EMPLOYEE_MISSING_PASSWORD", "(직원PASSWORD) 필수 항목을 입력해 주세요."),
    EMPLOYEE_INVALID_LENGTH_PASSWORD("EMPLOYEE_INVALID_LENGTH_PASSWORD", "(직원PASSWORD) 글자 수를 확인해 주세요."),
    EMPLOYEE_INVALID_PATTERN_PASSWORD("EMPLOYEE_INVALID_PATTERN_PASSWORD", "(직원PASSWORD) 영문/숫자/특수문자로 입력해 주세요.")
    ;

    private String code;
    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static void responseError(String field, ResponseCodeEnum responseCodeEnum, Errors errors) {
        errors.rejectValue(field, responseCodeEnum.code, responseCodeEnum.message);
    }
}
