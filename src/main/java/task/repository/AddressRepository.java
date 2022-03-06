package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Address;
import task.entity.Company;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
