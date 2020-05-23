package hanq.groupware.co.kr.zuul.api.auth.sign.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class SignInParam implements Serializable {

	@ApiModelProperty(required = true, value = "직원ID")
	@NotNull(message = "(직원ID) 필수 항목을 입력해 주세요.")
	private String employeeId;

	@ApiModelProperty(required = true, value = "직원PASSWORD")
	@NotNull(message = "(직원PASSWORD) 필수 항목을 입력해 주세요.")
	private String employeePassword;
}
