package hanq.groupware.co.kr.employee.roles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hanq.groupware.co.kr.employee.employee.domain.Employee;
import hanq.groupware.co.kr.employee.roles.domain.RolesEmployee;

public interface RolesRepository extends JpaRepository<RolesEmployee, Long> {
	Optional<RolesEmployee> findAllByEmployeeNo(Long employeeNo);
}
