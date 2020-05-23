package hanq.groupware.co.kr.zuul.api.auth.sign.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SignUpParam {

    @Id
    private String employee_no;

    private String employee_id;
    private String employee_password;
    private String employee_name;
    private String employee_gender;
    private String employee_position;
    private String employee_age;
    private String employee_type;
    private String employee_join_date;
    private String employee_resign_date;
    private String employee_status;
    private String reg_date;
    private String upd_date;
}
