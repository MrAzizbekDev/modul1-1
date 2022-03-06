package task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import task.dto.DepartmentDto;
import task.dto.WorkerDto;
import task.entity.Address;
import task.entity.Company;
import task.entity.Department;
import task.entity.Worker;
import task.repository.AddressRepository;
import task.repository.CompanyRepository;
import task.repository.DepartmentRepository;
import task.repository.WorkerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerService {
    final DepartmentRepository departmentRepository;
    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    public HttpEntity<?> add(WorkerDto workerDto) {
        Department department = departmentRepository.getById(workerDto.getDepId());
        Address address = new Address(workerDto.getStreet(), workerDto.getHomeNumber());
        Address address1 = addressRepository.save(address);
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setAddress(address1);
        worker.setDepartment(department);
        Worker save = workerRepository.save(worker);
        return ResponseEntity.ok().body(save);
    }

    public HttpEntity<?> edit(Integer id, WorkerDto workerDto) {
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isPresent()){
            Department department = departmentRepository.getById(workerDto.getDepId());
            Address address = addressRepository.getById(id);
            address.setStreet(workerDto.getStreet());
            address.setHomeNumber(workerDto.getHomeNumber());
            Address save = addressRepository.save(address);
            Worker worker = optional.get();
            worker.setName(workerDto.getName());
            worker.setAddress(save);
            worker.setDepartment(department);
            Worker save1 = workerRepository.save(worker);
            return ResponseEntity.status(402).body(save1);
        }
        return ResponseEntity.ok().body("Department Not found");
    }
}
