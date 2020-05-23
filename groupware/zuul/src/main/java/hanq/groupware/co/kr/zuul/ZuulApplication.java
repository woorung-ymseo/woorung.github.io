package hanq.groupware.co.kr.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableZuulProxy // zuul
@EnableDiscoveryClient // zuul => Eureka client
@EnableJdbcHttpSession
@EnableFeignClients // feign
@SpringBootApplication
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

}
