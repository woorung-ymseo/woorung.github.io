package hanq.groupware.co.kr.zuul.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import hanq.groupware.co.kr.zuul.core.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

/**
 * Authentication Filter
 * 
 * JWT 토큰 유효성 & 권한 부여 Filter
 *  
 * @author user
 *
 */
@RequiredArgsConstructor
public class HanqAuthenticationFilter // extends GenericFilterBean 
{
	
//	private final JwtTokenProvider jwtTokenProvider;
//	
//	/**
//	 * JWT 유효성 검사 및 권한 부여
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		String token = this.jwtTokenProvider.getResolveToken((HttpServletRequest) request);
//		
//		if (StringUtils.isNotEmpty(token) && this.jwtTokenProvider.validateToken(token)) {
//			Authentication auth = this.jwtTokenProvider.getAuthentication(token);
//			
//			SecurityContextHolder.getContext().setAuthentication(auth);
//		}
//		
//		chain.doFilter(request, response);
//	}
}
