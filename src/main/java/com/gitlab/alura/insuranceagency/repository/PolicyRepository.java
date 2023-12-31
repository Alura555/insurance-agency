package com.gitlab.alura.insuranceagency.repository;

import com.gitlab.alura.insuranceagency.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy> {
    @Override
    List<Policy> findAll();

    @Override
    Optional<Policy> findById(Long id);

    Page<Policy> findAll(Specification<Policy> spec, Pageable pageable);

    Optional<Policy> findOne(Specification<Policy> spec);

    Optional<Policy> findByIdAndIsActive(Long applicationId, boolean b);
}
