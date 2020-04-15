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

	@NotNull(message = "���̵�� �ʼ� �Դϴ�.")
	private String employeeId;
	
	@NotNull(message = "��й�ȣ�� �ʼ� �Դϴ�.")
	private String employeePassword;
}
