package hanq.groupware.co.kr.employee.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hanq.groupware.co.kr.employee.employee.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByUsername(String employeeId);
	
	Optional<Employee> findByEmployeeId(String employeeId);
	
	Optional<Employee> findByEmployeeIdAndEmployeePassword(String employeeId, String employeePassword);
}
