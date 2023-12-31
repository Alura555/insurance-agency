package com.gitlab.alura.insuranceagency.config;

import com.gitlab.alura.insuranceagency.mapper.CompanyMapper;
import com.gitlab.alura.insuranceagency.mapper.DocumentTypeMapper;
import com.gitlab.alura.insuranceagency.mapper.InsuranceTypeMapper;
import com.gitlab.alura.insuranceagency.mapper.OfferMapper;
import com.gitlab.alura.insuranceagency.mapper.PolicyMapper;
import com.gitlab.alura.insuranceagency.mapper.RoleMapper;
import com.gitlab.alura.insuranceagency.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {
    @Bean
    public OfferMapper offerMapper() {
        return Mappers.getMapper(OfferMapper.class);
    }
    @Bean
    public InsuranceTypeMapper insuranceTypeMapper() {
        return Mappers.getMapper(InsuranceTypeMapper.class);
    }

    @Bean
    public PolicyMapper policyMapper(){
        return Mappers.getMapper(PolicyMapper.class);
    }

    @Bean
    public DocumentTypeMapper documentTypeMapper(){
        return Mappers.getMapper(DocumentTypeMapper.class);
    }

    @Bean
    public CompanyMapper companyMapper(){
        return Mappers.getMapper(CompanyMapper.class);
    }

    @Bean
    public RoleMapper roleMapper(){
        return Mappers.getMapper(RoleMapper.class);
    }

    @Bean
    public UserMapper userMapper(){
        return Mappers.getMapper(UserMapper.class);
    }
}
