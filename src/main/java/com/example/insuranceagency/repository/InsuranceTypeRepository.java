package com.example.insuranceagency.repository;

import com.example.insuranceagency.entity.InsuranceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Long> {
    @Override
    List<InsuranceType> findAll();

    List<InsuranceType> findAllByIsActive(Boolean isActive);

    Page<InsuranceType> findAllByIsActive(Boolean isActive, Pageable pageable);
}