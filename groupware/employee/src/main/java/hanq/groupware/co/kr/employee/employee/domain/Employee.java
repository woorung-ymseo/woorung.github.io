package hanq.groupware.co.kr.employee.employee.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;
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

	private String username;
	
	@Id
	@Column(name = "EMPLOYEE_NO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeNo;
	
	private String employeeId; 
	
	private String employeePassword;
	
	private String employeeName;
	
	private String employeeGender;
	
	private String employeePosition;
	
	private int employeeAge;
	
	private String employeeType;
	
	@Temporal(TemporalType.DATE)
	private Date employeeJoinDate;
	
	@Temporal(TemporalType.DATE)
	private Date employeeResignDate;
	
	private String employeeStatus;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_NO")
    private List<RolesEmployee> roles = new ArrayList<>();
	
//	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, targetEntity= RolesEmployee.class) 
//	@OrderBy("role")
//	private List<RolesEmployee> roles = new ArrayList<>();

	@Builder
	public Employee(Long employeeNo, String employeeId, String employeePassword, String employeeName, String employeeGender,
			String employeePosition, int employeeAge, String employeeType, Date employeeJoinDate,
			Date employeeResignDate, String employeeStatus, List<RolesEmployee> roles) {
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
