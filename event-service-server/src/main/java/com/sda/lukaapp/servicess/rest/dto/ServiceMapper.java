package com.sda.lukaapp.servicess.rest.dto;

import com.sda.lukaapp.servicess.domain.Services;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    // dto to entity conversions

    public Services toEntity(CreateServiceRequest request) {
        Services services = new Services();
        services.setType(request.getType());
        services.setDetails(request.getDetails());
        return services;
    }

    // entity to dto conversions

    public List<ServiceResponse> toDto(List<Services> services) {
        return services.stream()
                .map(service -> this.toDto(service))
                .collect(Collectors.toList());
    }

    public List<ServiceResponse> toDtoOldWay(List<Services> givenList) {
        // create new list for modified objects
        List<ServiceResponse> resultList = new ArrayList<>();

        // for each item in given list
        for (Services services : givenList) {

            // transform to dto (by copying all fields)

            ServiceResponse serviceResponse = this.toDto(services);

            // add transformed item to the new list
            resultList.add(serviceResponse);
        }
        return resultList;
    }

    public ServiceResponse toDto(Services services) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId(services.getId());
        serviceResponse.setType(services.getType());
        serviceResponse.setDetails(services.getDetails());
        return serviceResponse;
    }

    // mapping used for partial update

    public void map(UpdateServiceRequest request, Services services) {
        if (request.getType() != null) {
            services.setType(request.getType());
        }
        if (request.getDetails() != null) {
            services.setDetails(request.getDetails());
        }

    }
}


