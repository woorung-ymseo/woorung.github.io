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
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic() // REST API
                .disable()
            .csrf() // JWT
                .disable() 
            .sessionManagement() // JWT
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and()
                .authorizeRequests()
//                .anyRequest().denyAll();
                .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	

}
