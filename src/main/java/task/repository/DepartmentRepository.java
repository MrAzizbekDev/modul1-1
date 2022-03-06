package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Address;
import task.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
