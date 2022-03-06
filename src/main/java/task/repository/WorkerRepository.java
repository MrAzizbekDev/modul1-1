package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Department;
import task.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
}
