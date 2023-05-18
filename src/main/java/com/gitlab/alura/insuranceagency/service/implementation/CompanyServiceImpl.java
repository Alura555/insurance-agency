package com.gitlab.alura.insuranceagency.service.implementation;

import com.gitlab.alura.insuranceagency.repository.CompanyRepository;
import com.gitlab.alura.insuranceagency.entity.Company;
import com.gitlab.alura.insuranceagency.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getActiveCompanies(){
        return companyRepository.findAllByIsActive(true);
    }

    @Override
    public List<Company> getPopularCompanies(int n) {
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        return companyRepository.findAllByIsActive(true)
                .stream()
                .sorted(Comparator.comparing(Company::getActiveOffersCount).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}