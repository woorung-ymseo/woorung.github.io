package hanq.groupware.co.kr.zuul.api.auth.sign.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "로그인 정보")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignIn {

	@ApiModelProperty(value = "Access Token")
	private String token;

	@ApiModelProperty(value = "직원ID")
	private String employeeId;
}
