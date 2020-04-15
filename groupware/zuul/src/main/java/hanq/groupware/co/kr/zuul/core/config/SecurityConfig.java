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
            .httpBasic() // REST API를 사용하므로 기본 인증 설정을 사용하지 않음
                .disable() 
            .csrf() // JWT 토큰 인증 방식을 사용하므로 CSRF 보안이 필요 없어 비활성화 처리한다.
                .disable() 
            .sessionManagement() // JWT 토큰 인증 방식을 사용하여 세션은 생성하지 않는다.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and()
                .authorizeRequests()
//                    .antMatchers("/api/employee/signin/**", "/api/employee/signup/**") 
//                        .permitAll() // 회원 가입, 로그인은 누구나 접근 가능
//                    .antMatchers("/exception/**").permitAll() // hellowworld로 시작하는 GET요청 리소스는 누구나 접근가능
//                    .antMatchers(HttpMethod.GET, "/hello")
//                        .permitAll() // /hello로 접근하는 HTTP GET 메소드는 누구나 접근 가능
                   
                   .anyRequest().permitAll();
//        .anyRequest().authenticated();
//                    .anyRequest()
//                        .hasRole("EMPLOYEE") // 그 외 모든 요청들은 EMPLOYEE 역할을 가진 회원만 접근 가능
//            .and() // Jwt 토큰 여부 체크
//            	.exceptionHandling()
//            		.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//        	.and() // Jwt Token 필터를 id/password 인증 필터 전 단계에 주입
//        		.addFilterBefore(new HanqAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        	
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
