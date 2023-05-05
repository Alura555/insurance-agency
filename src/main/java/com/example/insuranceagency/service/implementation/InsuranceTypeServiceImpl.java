package com.example.insuranceagency.service.implementation;

import com.example.insuranceagency.dto.InsuranceTypeDto;
import com.example.insuranceagency.entity.InsuranceType;
import com.example.insuranceagency.mapper.InsuranceTypeMapper;
import com.example.insuranceagency.repository.InsuranceTypeRepository;
import com.example.insuranceagency.service.InsuranceTypeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceTypeServiceImpl implements InsuranceTypeService {
    private final InsuranceTypeRepository insuranceTypeRepository;

    private final InsuranceTypeMapper insuranceTypeMapper;

    public InsuranceTypeServiceImpl(InsuranceTypeRepository insuranceTypeRepository,
                                    InsuranceTypeMapper insuranceTypeMapper) {
        this.insuranceTypeRepository = insuranceTypeRepository;
        this.insuranceTypeMapper = insuranceTypeMapper;
    }


    public List<InsuranceTypeDto> getInsuranceTypes(){
        return insuranceTypeRepository.findAllByIsActive(true)
                .stream()
                .map(insuranceTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InsuranceTypeDto> getPopularInsuranceTypes(int n) {
        if (n <= 0){
            throw new IllegalArgumentException();
        }

        return insuranceTypeRepository.findAllByIsActive(true)
                .stream()
                .sorted(Comparator.comparing(InsuranceType::getActiveOffersCount).reversed())
                .limit(n)
                .map(insuranceTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
