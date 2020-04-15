package hanq.groupware.co.kr.zuul.core.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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
public class JwtTokenProvider {

	// secret Key
	@Value("${jwt.secret}")
	private String secretKey;
	
	// Token Valid Time 
	@Value("${jwt.expiration-time}")
	private String tokenValidMilisecond;
	
	/**
	 * 의존성이 완료된 후 초기화
	 * 
	 * secret Key 암호화
	 */
	@PostConstruct
	protected void init() {
		this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
	}
	
	/**
	 * JWT 토큰 생성
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
					.setClaims(claims)	// 회원정보 데이터
					.setIssuedAt(now) 	// 토큰발행일
					.setExpiration(new Date(now.getTime() + Long.parseLong(this.tokenValidMilisecond)))	// 만료일
					.signWith(SignatureAlgorithm.HS256, this.secretKey)	// 키 암호화
					.compact();
	}
	
	/**
	 * JWT에서 employee Id 추출
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
	 * JWT 인증정보 조회
	 * 
	 * @param token
	 * @return
	 */
	public Authentication getAuthentication(String token) {
		System.out.println("######### this.getUserName(token) : " + this.getEmployeeId(token));
		
		UserDetails userDetails = null;
//		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getEmployeeId(token));
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	/**
	 * Header에서 Token 얻기
	 * 
	 * @param request
	 * @return
	 */
	public String getResolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	/**
	 * 토큰 유효성, 만료일자 체크
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
