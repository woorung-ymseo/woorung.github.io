package hanq.groupware.co.kr.business.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/business")
public class BusinessController {

	@GetMapping("/get/find/{businessNo}/{employeeId}")
	public String getBusiness(@PathVariable String businessNo,
			@PathVariable String employeeId) {
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("### businessNo :  " + businessNo);
		
//		throw new RuntimeException("¤¾¤·");
		
		return "[ Business :: businessNo=" + businessNo + " | employeeId=" + employeeId + " ]";
	}
	
}
