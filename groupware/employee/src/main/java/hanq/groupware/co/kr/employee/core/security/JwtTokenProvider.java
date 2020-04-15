package hanq.groupware.co.kr.employee.core.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import hanq.groupware.co.kr.employee.employee.repository.EmployeeRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Value("jwt.secret")
	private String secretKey;
	
	private long tokenValidMilisecond = 1000L * 60 * 60; // 1�ð�
	
	/**
	 * �������� �Ϸ�� �� �ʱ�ȭ
	 */
	@PostConstruct
	protected void init() {
		this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
	}
	
	/**
	 * JWT ��ū ����
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
						.setIssuedAt(now) 	// ��ū������`
						.setExpiration(new Date(now.getTime() + this.tokenValidMilisecond))
						.signWith(SignatureAlgorithm.HS256, this.secretKey)
						.compact();
	}
	
	/**
	 * JWT���� username ����
	 * 
	 * @param token
	 * @return
	 */
	public String getUserName(String token) {
		return Jwts.parser()
					.setSigningKey(this.secretKey)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}
	
	/**
	 * JWT �������� ��ȸ
	 * 
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		System.out.println("######### this.getUserName(token) : " + this.getUserName(token));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserName(token));
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	/**
	 * Header���� Token ���
	 * 
	 * @param request
	 * @return
	 */
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	/**
	 * ��ū ��ȿ��, �������� üũ
	 * 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(this.secretKey)
					.parseClaimsJws(token);
			
			return !claims.getBody()
					.getExpiration()
					.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
