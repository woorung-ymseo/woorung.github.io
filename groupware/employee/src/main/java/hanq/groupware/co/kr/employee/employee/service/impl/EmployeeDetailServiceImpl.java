package hanq.groupware.co.kr.employee.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeDetailServiceImpl implements UserDetailsService {

	private final EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(Long.parseLong("1"));
		
		System.out.println("### gd : " + employee.get().toString());
		
		return employeeRepository.findById(Long.parseLong("1"))
//		return employeeRepository.findByEmployeeId(employeeId)
				.map(entity -> {
					List<GrantedAuthority> authority = new ArrayList<>();
					
					System.out.println("#### entity : " + entity.toString());
					
//					authority.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
					authority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
					authority.add(new SimpleGrantedAuthority("ROLE_GUEST"));
					
					User a = new User(employeeId, entity.getPassword(), authority);
					
					a.getAuthorities();
					
					System.out.println("###3 a.getAuthorities() : " + a.getAuthorities());
					
					return a;
				})
				.orElseThrow(() -> new UsernameNotFoundException("사용자 아이디가 존재하지 않습니다"));
	}
}
