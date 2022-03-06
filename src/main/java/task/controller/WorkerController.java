package task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.WorkerDto;
import task.entity.Worker;
import task.repository.WorkerRepository;
import task.service.WorkerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {
   final WorkerRepository workerRepository;
   final WorkerService workerService;
    @GetMapping
    public HttpEntity<?>getAll(){
        List<Worker> all = workerRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?>getById(@PathVariable Integer id){
        Optional<Worker> companyOptional = workerRepository.findById(id);
        if (companyOptional.isPresent()) return ResponseEntity.ok().body(companyOptional.get());
        return ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
    }
    @PostMapping()
    public HttpEntity<?>add(@RequestBody WorkerDto workerDto){
        return workerService.add(workerDto);
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        return workerService.edit(id, workerDto);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>delete(@PathVariable Integer id){
        workerRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
