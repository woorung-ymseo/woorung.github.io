package hanq.groupware.co.kr.employee.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping("/api/employee")
public @interface HanqRestController {
	
//	public static final String baseMapping = "/api/employee";
//	
//	/**
//	 * Alias for {@link RequestMapping#value}.
//	 */
//	@AliasFor(annotation = RequestMapping.class)
//	String value() default baseMapping;
}
