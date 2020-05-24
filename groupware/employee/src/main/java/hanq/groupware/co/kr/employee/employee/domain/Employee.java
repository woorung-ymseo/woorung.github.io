package hanq.groupware.co.kr.employee.employee.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hanq.groupware.co.kr.employee.core.serializer.deserializer.GrantedAuthorityDeserializer;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "tb_employee")
@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee implements UserDetails {

	@Id
	@Column(name = "EMPLOYEE_NO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeNo;

	@NotNull(message = "(직원ID) 필수 항목을 입력해 주세요.")
	private String employeeId;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@NotNull(message = "(직원PASSWORD) 필수 항목을 입력해 주세요.")
	private String employeePassword;

	@NotNull(message = "(직원이름) 필수 항목을 입력해 주세요.")
	private String employeeName;

	@ColumnDefault(value = "M")
	private String employeeGender;

	@ColumnDefault(value = "01")
	private String employeePosition;

	@ColumnDefault(value = "20")
	private int employeeAge;

	@ColumnDefault(value = "01")
	private String employeeType;

	private String employeeJoinDate;

	private String employeeResignDate;

	@ColumnDefault(value = "01")
	private String employeeStatus;

	private String regDate;

	private String updDate;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_NO")
	private List<RolesEmployee> roles = new ArrayList<>();
	
	@Builder
	public Employee(Long employeeNo, String employeeId, String employeePassword, String employeeName, String employeeGender,
			String employeePosition, int employeeAge, String employeeType, String employeeJoinDate,
			String employeeResignDate, String regDate, String updDate, String employeeStatus, List<RolesEmployee> roles) {
		this.employeeNo = employeeNo;
		this.employeeId = employeeId;
		this.employeePassword = employeePassword;
		this.employeeName = employeeName;
		this.employeeGender = employeeGender;
		this.employeePosition = employeePosition;
		this.employeeAge = employeeAge;
		this.employeeType = employeeType;
		this.employeeJoinDate = employeeJoinDate;
		this.employeeResignDate = employeeResignDate;
		this.regDate = regDate;
		this.updDate = updDate;
		this.employeeStatus = employeeStatus;
		this.roles = roles;
	}

	public List<String> getAuthorityList() {
		List<String> lRoles 
			= this.roles.stream()
				.map(role -> {
					return role.getRole();
				}).collect(Collectors.toList());
		return lRoles;
	}

	@PrePersist
	public void prePersist() {
		regDate = LocalDateTime.now().toString();
		employeeJoinDate = LocalDateTime.now().toString();
	}

	@PreUpdate
	public void preUpdate() {
		updDate = LocalDateTime.now().toString();
	}

	@JsonDeserialize(using = GrantedAuthorityDeserializer.class)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles
					.stream()
					.map(role -> {
						return new SimpleGrantedAuthority(role.getRole());
					}).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.employeePassword;
	}

	@Override
	public String getUsername() {
		return this.employeeName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
