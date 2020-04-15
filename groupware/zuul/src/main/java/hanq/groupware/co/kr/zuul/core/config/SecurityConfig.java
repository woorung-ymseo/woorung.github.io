package hanq.groupware.co.kr.zuul.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hanq.groupware.co.kr.zuul.core.filter.HanqAuthenticationFilter;
import hanq.groupware.co.kr.zuul.core.security.JwtAuthenticationEntryPoint;
import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import hanq.groupware.co.kr.zuul.employee.service.FeignEmployeeService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;
	
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic() // REST API�� ����ϹǷ� �⺻ ���� ������ ������� ����
                .disable() 
            .csrf() // JWT ��ū ���� ����� ����ϹǷ� CSRF ������ �ʿ� ���� ��Ȱ��ȭ ó���Ѵ�.
                .disable() 
            .sessionManagement() // JWT ��ū ���� ����� ����Ͽ� ������ �������� �ʴ´�.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and()
                .authorizeRequests()
//                    .antMatchers("/api/employee/signin/**", "/api/employee/signup/**") 
//                        .permitAll() // ȸ�� ����, �α����� ������ ���� ����
//                    .antMatchers("/exception/**").permitAll() // hellowworld�� �����ϴ� GET��û ���ҽ��� ������ ���ٰ���
//                    .antMatchers(HttpMethod.GET, "/hello")
//                        .permitAll() // /hello�� �����ϴ� HTTP GET �޼ҵ�� ������ ���� ����
                   
                   .anyRequest().permitAll();
//        .anyRequest().authenticated();
//                    .anyRequest()
//                        .hasRole("EMPLOYEE") // �� �� ��� ��û���� EMPLOYEE ������ ���� ȸ���� ���� ����
//            .and() // Jwt ��ū ���� üũ
//            	.exceptionHandling()
//            		.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//        	.and() // Jwt Token ���͸� id/password ���� ���� �� �ܰ迡 ����
//        		.addFilterBefore(new HanqAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        	
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
