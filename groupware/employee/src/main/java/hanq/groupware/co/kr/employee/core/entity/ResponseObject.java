package hanq.groupware.co.kr.employee.core.entity;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Builder.Default
	private HttpStatus status = HttpStatus.OK;
	
	@Builder.Default
	private String resultStatus = "SUCCESS";
	
	@Builder.Default
	private String resultCode = "S000";
	
	@Builder.Default
	private String resultMessage = "";
	
	@Builder.Default
	private T body = null;
	
//	@Builder
//	public ResponseObject(HttpStatus status, String resultStatus, String resultCode, String resultMessage,
//			T body) {
//		this.status = 200;
//	    this.resultStatus = "SUCCESS";
//	    this.resultCode = "0000";
//	    this.resultMessage = resultMessage;
//	    this.body = body;
//	}
}
