package hanq.groupware.co.kr.zuul.core.utils;

import com.netflix.ribbon.proxy.annotation.Http;
import hanq.groupware.co.kr.zuul.core.entity.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ResponseObjectUtils {

    /**
     * Response Ok With Body
     *
     * @param body
     * @return
     */
    public ResponseObject responseForOk(Object body) {
        return this.responseForOk(body, "");
    }

    /**
     * Response Ok With Message
     * @param message
     * @return
     */
    public ResponseObject responseForOk(String message) {
        return this.responseForOk(message, message);
    }

    /**
     * Response Ok With Body, Message
     * @param body
     * @param message
     * @return
     */
    public ResponseObject responseForOk(Object body, String message) {
        return ResponseObject.builder()
                .body(body)
                .status(HttpStatus.OK)
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultStatus(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(message)
                .build();
    }

    /**
     * Response Errors With HttpStatus, Message
     *
     * @param httpStatus
     * @param message
     * @return
     */
    public ResponseObject responseForErrors(HttpStatus httpStatus, String message) {
        return ResponseObject.builder()
                .status(httpStatus)
                .resultCode(String.valueOf(httpStatus.value()))
                .resultStatus(String.valueOf(httpStatus.value()))
                .resultMessage(message)
                .build();
    }

    /**
     * Response Errors With Errors
     *
     * @param errors
     * @return
     */
    public ResponseObject responseForErrors(Errors errors) {
        return this.responseForErrors(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
    }

    /**
     * Response Errors With Message
     *
     * @param message
     * @return
     */
    public ResponseObject responseForErrors(String message) {
        return this.responseForErrors(HttpStatus.BAD_REQUEST, message);
    }
}
