package com.gitlab.alura.insuranceagency.service.implementation;

import com.gitlab.alura.insuranceagency.exception.NotFoundException;
import com.gitlab.alura.insuranceagency.repository.InsuranceTypeRepository;
import com.gitlab.alura.insuranceagency.dto.InsuranceTypeDto;
import com.gitlab.alura.insuranceagency.entity.InsuranceType;
import com.gitlab.alura.insuranceagency.mapper.InsuranceTypeMapper;
import com.gitlab.alura.insuranceagency.service.InsuranceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceTypeServiceImpl implements InsuranceTypeService {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceTypeServiceImpl.class);

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

    @Override
    public InsuranceType getById(Long id) {
        return insuranceTypeRepository.findByIdAndIsActive(id, true)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public InsuranceTypeDto getDtoById(Long id) {
        return insuranceTypeMapper.toDto(getById(id));
    }

    @Override
    public Page<InsuranceTypeDto> getAllActive(Pageable pageable) {
        Page<InsuranceType> insuranceTypePage = insuranceTypeRepository.findAllByIsActive(pageable, true);
        List<InsuranceTypeDto> insuranceTypeDtoList = insuranceTypePage
                .getContent()
                .stream()
                .map(insuranceTypeMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(insuranceTypeDtoList, pageable, insuranceTypePage.getTotalElements());
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting insurance type with ID: {}", id);
        InsuranceType insuranceType = getById(id);
        insuranceType.setActive(false);
        insuranceTypeRepository.save(insuranceType);
        logger.info("Insurance type deleted successfully");
    }

    @Override
    public void createInsuranceType(InsuranceTypeDto insuranceTypeDto) {
        logger.info("Creating new insurance type");
        InsuranceType insuranceType = insuranceTypeMapper.toEntity(insuranceTypeDto);
        insuranceType.setActive(true);
        insuranceType = insuranceTypeRepository.save(insuranceType);
        logger.info("New insurance type created successfully with ID: {}", insuranceType.getId());
    }

    @Override
    public void updateInsuranceType(InsuranceTypeDto insuranceTypeDto) {
        deleteById(insuranceTypeDto.getId());

        insuranceTypeDto.setId(null);
        createInsuranceType(insuranceTypeDto);
    }
}
