package hanq.groupware.co.kr.zuul.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignResDto {

	private String token;
	
	private String employeeId;
}
