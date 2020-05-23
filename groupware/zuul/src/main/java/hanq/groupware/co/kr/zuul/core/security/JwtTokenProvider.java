package hanq.groupware.co.kr.zuul.core.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import hanq.groupware.co.kr.zuul.api.employee.employee.domain.Employee;
import hanq.groupware.co.kr.zuul.api.employee.service.FeignEmployeeService;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT Provider
 * 
 * @author user
 *
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	// secret Key
	@Value("${jwt.secret}")
	private String secretKey;
	
	// Token Valid Time 
	@Value("${jwt.expiration-time}")
	private String tokenValidMilisecond;

	private final FeignEmployeeService feignEmployeeService;
	
	/**
	 *
	 * 
	 * secret Key
	 */
	@PostConstruct
	protected void init() {
		this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
	}
	
	/**
	 * JWT
	 * 
	 * @param employeeId
	 * @param roles
	 * @return
	 */
	public String createToken(String employeeId, List<String> roles) {
		Date now = new Date();
		
		Claims claims = Jwts.claims().setSubject(employeeId);
		
		claims.put("roles", roles);
		
		return Jwts.builder()
					.setClaims(claims)	// ȸ������ ������
					.setIssuedAt(now) 	// ��ū������
					.setExpiration(new Date(now.getTime() + Long.parseLong(this.tokenValidMilisecond)))	// ������
					.signWith(SignatureAlgorithm.HS256, this.secretKey)	// Ű ��ȣȭ
					.compact();
	}
	
	/**
	 * JWT
	 * 
	 * @param token
	 * @return
	 */
	public String getEmployeeId(String token) {
		return Jwts.parser()
					.setSigningKey(this.secretKey)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}
	
	/**
	 * JWT
	 * 
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		System.out.println("######### this.getUserName(token) : " + this.getEmployeeId(token));

		ResponseObject<Employee> resEmployee = feignEmployeeService.getEmployeeInfo(this.getEmployeeId(token));

		Employee employee = resEmployee.getBody();

		return new UsernamePasswordAuthenticationToken(employee, "", employee.getAuthorities());
	}
	
	/**
	 * Header
	 * 
	 * @param request
	 * @return
	 */
	public String getResolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	/**
	 *
	 * 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts
									.parser()
									.setSigningKey(this.secretKey)
									.parseClaimsJws(token);

			return !claims
						.getBody()
						.getExpiration()
						.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
