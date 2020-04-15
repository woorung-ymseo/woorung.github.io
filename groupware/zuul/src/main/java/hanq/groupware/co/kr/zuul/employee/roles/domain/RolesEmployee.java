package hanq.groupware.co.kr.zuul.employee.roles.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Id
	@Column(name = "EMPLOYEE_NO")
	private Long employeeNo;
	
	@Id
	@Column(name = "ROLE")
	private String role; 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="employeeNo")
//	private Employee employee;
}
