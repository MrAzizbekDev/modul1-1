package task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import task.dto.CompanyDto;
import task.dto.DepartmentDto;
import task.entity.Address;
import task.entity.Company;
import task.entity.Department;
import task.repository.AddressRepository;
import task.repository.CompanyRepository;
import task.repository.DepartmentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;

    public HttpEntity<?> add(DepartmentDto departmentDto) {
        Company company = companyRepository.getById(departmentDto.getCompId());
        Department department = new Department();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        Department save = departmentRepository.save(department);
        return ResponseEntity.ok().body(save);
    }

    public HttpEntity<?> edit(Integer id, DepartmentDto departmentDto) {
        if (departmentRepository.findById(id).isPresent()) {
            Department department = departmentRepository.findById(id).get();
            department.setName(departmentDto.getName());
            Company byId = companyRepository.getById(id);
            department.setCompany(byId);
            Department save = departmentRepository.save(department);
            return ResponseEntity.status(402).body(save);
        }
        return ResponseEntity.ok().body("Department Not found");
    }
}
