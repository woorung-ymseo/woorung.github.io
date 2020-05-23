package hanq.groupware.co.kr.zuul.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import hanq.groupware.co.kr.zuul.core.context.ResponseContextHolder;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

/**
 * Authentication Filter
 * 
 * JWT Filter
 *  
 * @author user
 *
 */
@RequiredArgsConstructor
@WebFilter
public class HanqAuthenticationFilter extends GenericFilterBean
{
	
	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * JWT
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = this.jwtTokenProvider.getResolveToken((HttpServletRequest) request);

		if (StringUtils.isNotEmpty(token) && this.jwtTokenProvider.validateToken(token)) {
			Authentication auth = this.jwtTokenProvider.getAuthentication(token);

			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		chain.doFilter(request, response);
	}
}
