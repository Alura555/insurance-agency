package com.example.insuranceagency.mapper;

import com.example.insuranceagency.dto.OfferDto;
import com.example.insuranceagency.entity.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = InsuranceTypeMapper.class)
public interface OfferMapper {
    @Mapping(target = "period", expression = "java(formatPeriod(offer.getPeriodInMonths()))")
    @Mapping(target = "companyName", source = "offer.company.name")
    @Mapping(source = "price", target = "price", numberFormat = "$#.##")
    OfferDto toOfferDto(Offer offer);


    default String formatPeriod(int periodInMonths) {
        int years = periodInMonths / 12;
        int months = periodInMonths % 12;
        StringBuilder stringBuilder = new StringBuilder();
        if (years > 0) {
            stringBuilder.append(years)
                    .append(" year")
                    .append(years != 1 ? "s":"");
        }
        if (months > 0) {
            stringBuilder
                    .append(years > 0 ? " " : "")
                    .append(months)
                    .append(" month")
                    .append(months != 1 ? "s":"");
        }
        return stringBuilder.toString();
    }
}