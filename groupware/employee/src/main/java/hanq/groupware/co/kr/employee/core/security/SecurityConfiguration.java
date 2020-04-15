package hanq.groupware.co.kr.employee.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hanq.groupware.co.kr.employee.core.filter.JwtAuthenticationFilter;
import hanq.groupware.co.kr.employee.employee.service.impl.EmployeeDetailServiceImpl;
import hanq.groupware.co.kr.employee.sign.service.SignService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;
	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	private EmployeeDetailServiceImpl employeeDetailServiceImpl;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	/**
     * 스프링 시큐리티 룰 예외 규칙
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//            .antMatchers("/css/**", 
//                "/js/**", 
//                "/img/**", 
//                "/lib/**", 
//                "/h2-console/**", // h2 db console
//                "/v2/api-docs", // swagger api-doc
//                "/webjars/**", // swagger assets
//                "/swagger-ui.html", // swagger html
//                "/swagger-resources/**", // swagger resources
//                "/swagger/**", // swagger 
//                "/api/**",
//                "/error");
    }
    
    /**
     * 스프링 시큐리티 룰
     */
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
//                   .anyRequest().authenticated()
//                    .anyRequest()
//                    .antMatchers(HttpMethod.GET)
//                        .hasRole("EMPLOYEE") // 그 외 모든 요청들은 EMPLOYEE 역할을 가진 회원만 접근 가능
//            .and() // Jwt 토큰 여부 체크
//            	.exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
//        	.and() // Jwt Token 필터를 id/password 인증 필터 전 단계에 주입
//        		.addFilterBefore(new JwtAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        	
    }
//    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	

}
