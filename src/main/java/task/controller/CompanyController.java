package task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.CompanyDto;
import task.entity.Company;
import task.repository.CompanyRepository;
import task.service.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
   final CompanyRepository companyRepository;
   final CompanyService companyService;
    @GetMapping
    public HttpEntity<?>getAll(){
        List<Company> all = companyRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?>getById(@PathVariable Integer id){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) return ResponseEntity.ok().body(companyOptional.get());
        return ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public HttpEntity<?>add(@RequestBody CompanyDto companyDto){
        return companyService.add(companyDto);
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id,@RequestBody CompanyDto companyDto){
        return companyService.edit(id, companyDto);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Integer id){
        companyRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
