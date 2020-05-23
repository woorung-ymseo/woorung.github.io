package hanq.groupware.co.kr.employee.sign.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignReqDto  implements Serializable {

	@NotNull(message = "(직원ID) 필수 항목을 입력해 주세요.")
	private String employeeId;

	@NotNull(message = "(직원PASSWORD) 필수 항목을 입력해 주세요.")
	private String employeePassword;
}
