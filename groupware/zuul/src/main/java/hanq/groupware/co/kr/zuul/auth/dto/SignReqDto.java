package hanq.groupware.co.kr.zuul.auth.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class SignReqDto  implements Serializable {

	@NotNull(message = "아이디는 필수 입니다.")
	private String employeeId;
	
	@NotNull(message = "비밀번호는 필수 입니다.")
	private String employeePassword;
}
