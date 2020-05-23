package hanq.groupware.co.kr.zuul.core.config;

import hanq.groupware.co.kr.zuul.core.filter.HanqAuthenticationFilter;
import hanq.groupware.co.kr.zuul.core.security.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
            .httpBasic() // REST API
                .disable() 
            .csrf() // JWT
                .disable() 
            .sessionManagement() // JWT
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers("/api/hanq/auth/signin/**", "/api/hanq/auth/signup/**", "/exception/**")
						.permitAll()
					.antMatchers("/api/hanq/employee/**")
						.hasRole("EMPLOYEE")
					.anyRequest().denyAll()
            .and() // Jwt
            	.exceptionHandling()
            		.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        	.and() // Jwt Token
				.antMatcher("/api/hanq/employee/**")
        		.addFilterBefore(new HanqAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
