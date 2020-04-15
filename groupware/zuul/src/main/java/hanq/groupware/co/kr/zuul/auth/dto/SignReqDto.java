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

	@NotNull(message = "���̵�� �ʼ� �Դϴ�.")
	private String employeeId;
	
	@NotNull(message = "��й�ȣ�� �ʼ� �Դϴ�.")
	private String employeePassword;
}
