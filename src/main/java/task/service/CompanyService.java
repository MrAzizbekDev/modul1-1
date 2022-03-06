package task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import task.dto.CompanyDto;
import task.entity.Address;
import task.entity.Company;
import task.repository.AddressRepository;
import task.repository.CompanyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    final AddressRepository addressRepository;
    final CompanyRepository companyRepository;
    public HttpEntity<?> add(CompanyDto companyDto) {
        Address address = new Address( companyDto.getStreet(),companyDto.getHomeNumber());
        addressRepository.save(address);
        Company company = new Company();
        company.setAddress(address);
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Company save = companyRepository.save(company);
        return ResponseEntity.ok().body(save);
    }

    public HttpEntity<?> edit(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Address address = addressRepository.getById(id);
            address.setHomeNumber(companyDto.getHomeNumber());
            address.setStreet(companyDto.getStreet());
            addressRepository.save(address);
            Company company = optionalCompany.get();
            company.setDirectorName(companyDto.getDirectorName());
            company.setAddress(address);
            company.setCorpName(companyDto.getCorpName());
            Company save = companyRepository.save(company);
            return ResponseEntity.status(402).body(save);
        }
        return ResponseEntity.ok().body("Company Not found");
    }
}
