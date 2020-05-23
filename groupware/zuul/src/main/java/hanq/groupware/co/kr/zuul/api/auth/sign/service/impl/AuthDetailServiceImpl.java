package hanq.groupware.co.kr.zuul.api.auth.sign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hanq.groupware.co.kr.zuul.api.auth.sign.service.FeignAuthService;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthDetailServiceImpl implements UserDetailsService {
	
	private final FeignAuthService feignAuthService;

	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
		ResponseObject<List<String>> resRoles = feignAuthService.getEmployeeRoles(employeeId);
		
		if (resRoles != null && resRoles.getBody() != null) {
			List<GrantedAuthority> authority = new ArrayList<>();
			
			List<String> roles = resRoles.getBody();
			
			roles.stream().forEach(role -> {
				authority.add(new SimpleGrantedAuthority(role));
			});
			
			return new User(employeeId, "", authority);
		} else {
			throw new UsernameNotFoundException("등록된 직원이 없습니다.");
		}
	}
}

