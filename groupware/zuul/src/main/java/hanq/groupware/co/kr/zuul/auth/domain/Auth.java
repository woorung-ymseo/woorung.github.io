package hanq.groupware.co.kr.zuul.auth.domain;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface Auth extends Serializable {
	
	String getEmployeeId();

	String getEmployeePassword();

	Collection<? extends GrantedAuthority> getAuthorities();
	
	boolean isAccountNonExpired();

	boolean isAccountNonLocked();

	boolean isCredentialsNonExpired();

	boolean isEnabled();
}
