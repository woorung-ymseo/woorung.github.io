package hanq.groupware.co.kr.employee.core.filter;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import hanq.groupware.co.kr.employee.core.security.JwtTokenProvider;
import hanq.groupware.co.kr.employee.login.domain.Login;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean  {

	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * JWT Filter
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = this.jwtTokenProvider.resolveToken((HttpServletRequest) request);
		
		System.out.println("######## token  : " + token);
		
		if (StringUtils.isNotEmpty(token) && this.jwtTokenProvider.validateToken(token)) {
			Authentication auth = this.jwtTokenProvider.getAuthentication(token);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		chain.doFilter(request, response);
	}
}
