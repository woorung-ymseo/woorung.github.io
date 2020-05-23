package hanq.groupware.co.kr.zuul.api.employee.employee.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_roles_employee")
@Entity
@Getter
@Setter
@ToString(exclude = "employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RolesEmployee implements Serializable {

	@ApiModelProperty(value = "직원NO")
	@Id
	@Column(name = "EMPLOYEE_NO")
	private Long employeeNo;

	@ApiModelProperty(value = "직원 권한")
	@Id
	@Column(name = "ROLE")
	private String role; 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="employeeNo")
//	private Employee employee;
}
