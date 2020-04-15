package hanq.groupware.co.kr.employee.core.security.domain;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface HanqUser extends Serializable {

	Collection<? extends GrantedAuthority> getAuthorities();

	String getEmployeeId();

	String getEmployeePassword();
	
	boolean isAccountNonExpired();

	boolean isAccountNonLocked();

	boolean isCredentialsNonExpired();

	boolean isEnabled();
}
