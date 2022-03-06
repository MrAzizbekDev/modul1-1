package task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.DepartmentDto;
import task.entity.Department;
import task.repository.DepartmentRepository;
import task.repository.DepartmentRepository;
import task.service.DepartmentService;
import task.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
   final DepartmentRepository departmentRepository;
   final DepartmentService departmentService;
    @GetMapping
    public HttpEntity<?>getAll(){
        List<Department> all = departmentRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?>getById(@PathVariable Integer id){
        Optional<Department> companyOptional = departmentRepository.findById(id);
        if (companyOptional.isPresent()) return ResponseEntity.ok().body(companyOptional.get());
        return ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public HttpEntity<?>add(@RequestBody DepartmentDto departmentDto){
        return departmentService.add(departmentDto);
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        return departmentService.edit(id, departmentDto);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Integer id){
        departmentRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
