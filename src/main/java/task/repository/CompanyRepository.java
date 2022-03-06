package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
