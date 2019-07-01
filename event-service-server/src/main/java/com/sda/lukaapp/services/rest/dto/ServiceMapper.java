package com.sda.lukaapp.services.rest.dto;

import com.sda.lukaapp.services.domain.Service;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    // dto to entity conversions

    public Service toEntity(CreateServiceRequest request) {
        Service service = new Service();
        service.setType(request.getType());
        service.setDetails(request.getDetails());
        return service;
    }

    // entity to dto conversions

    public List<ServiceResponse> toDto(List<Service> services) {
        return services.stream()
                .map(service -> this.toDto(service))
                .collect(Collectors.toList());
    }

    public List<ServiceResponse> toDtoOldWay(List<Service> givenList) {
        // create new list for modified objects
        List<ServiceResponse> resultList = new ArrayList<>();

        // for each item in given list
        for (Service service : givenList) {

            // transform to dto (by copying all fields)

            ServiceResponse serviceResponse = this.toDto(service);

            // add transformed item to the new list
            resultList.add(serviceResponse);
        }
        return resultList;
    }

    public ServiceResponse toDto(Service service) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId(service.getId());
        serviceResponse.setType(service.getType());
        serviceResponse.setDetails(service.getDetails());
        return serviceResponse;
    }

    // mapping used for partial update

    public void map(UpdateServiceRequest request, Service service) {
        if (request.getType() != null) {
            service.setType(request.getType());
        }
        if (request.getDetails() != null) {
            service.setDetails(request.getDetails());
        }

    }
}


