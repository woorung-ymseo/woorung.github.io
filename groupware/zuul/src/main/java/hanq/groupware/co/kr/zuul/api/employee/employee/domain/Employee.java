package hanq.groupware.co.kr.zuul.api.employee.employee.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hanq.groupware.co.kr.zuul.core.serializer.deserializer.GrantedAuthorityDeserializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_employee")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee implements UserDetails {

	@PostConstruct
	public void init() {
		this.employeePosition = "01";
		this.employeeAge = 20;
		this.employeeType = "01";
		this.employeeStatus = "01";
	}

	@ApiModelProperty(value = "직원NO")
	@Id
	@Column(name = "EMPLOYEE_NO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeNo;

	@ApiModelProperty(value = "직원ID")
	@NotNull(message = "(직원ID) 필수 항목을 입력해 주세요.")
	@Size(min = 5, max = 20, message = "(직원ID) 글자 수를 확인해 주세요.")
	@Pattern(regexp = "^[A-Za-z[0-9]]{5,20}$",
				message = "(직원ID) 영문/숫자로 입력해 주세요.")
	private String employeeId;

	@ApiModelProperty(value = "직원PASSWORD")
	@NotNull(message = "(직원PASSWORD) 필수 항목을 입력해 주세요.")
	@Size(min = 8, max = 20, message = "(직원PASSWORD) 글자 수를 확인해 주세요.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",
				message = "(직원PASSWORD) 영문/숫자/특수문자로 입력해 주세요.")
	private String employeePassword;

	@ApiModelProperty(value = "직원이름")
	@NotNull(message = "(직원이름) 필수 항목을 입력해 주세요.")
	private String employeeName;

	@ApiModelProperty(value = "직원성별 (M:남성, W:여성")
	@ColumnDefault(value = "M")
	private String employeeGender;

	@ApiModelProperty(value = "직원직책 (01:사원, 02:대리, 03:과장, 04:차장, 05:부장, 06:이사, 07:전무, 08:대표")
	@ColumnDefault(value = "01")
	private String employeePosition;

	@ApiModelProperty(value = "직원나이")
	@NotNull(message = "(직원나이) 필수 항목을 입력해 주세요.")
	private int employeeAge;

	@ApiModelProperty(value = "고용형태 (01: 정직원, 02: 프리랜서, 03: 계약직, 04: 알바")
	@ColumnDefault(value = "01")
	private String employeeType;

	@ApiModelProperty(value = "입사일")
	private String employeeJoinDate;

	@ApiModelProperty(value = "퇴사일")
	private String employeeResignDate;

	@ApiModelProperty(value = "직원상태 (00: 퇴사, 01: 수습, 02: 정직원, 03: 휴가")
	@ColumnDefault(value = "01")
	private String employeeStatus;

	@ApiModelProperty(value = "등록일")
	private String regDate;

	@ApiModelProperty(value = "수정일")
	private String updDate;

	@ApiModelProperty(value = "직원권한")
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
		this.employeeStatus = employeeStatus;
		this.regDate = regDate;
		this.updDate = updDate;
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
