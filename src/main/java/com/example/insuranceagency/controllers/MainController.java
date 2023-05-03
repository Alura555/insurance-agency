package com.example.insuranceagency.controllers;

import com.example.insuranceagency.dtos.InsuranceTypeDto;
import com.example.insuranceagency.entities.Company;
import com.example.insuranceagency.services.CompanyService;
import com.example.insuranceagency.services.InsuranceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InsuranceTypeService insuranceTypeService;

    public static final int COMPANY_COUNT = 9;
    public static final int INSURANCE_TYPE_COUNT = 6;
    @GetMapping
    public String indexPage(Model model){
        List<Company> companies = companyService.getPopularCompanies(COMPANY_COUNT);
        model.addAttribute("companies", companies);

        List<InsuranceTypeDto> insuranceTypes = insuranceTypeService.getPopularInsuranceTypes(INSURANCE_TYPE_COUNT);
        model.addAttribute("types", insuranceTypes);
        return "index";
    }
}
