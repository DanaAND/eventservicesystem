package com.sda.lukaapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.exception.NotFoundException;
import com.sda.lukaapp.services.domain.Service;
import com.sda.lukaapp.services.domain.ServiceRepository;
import com.sda.lukaapp.services.rest.dto.CreateServiceRequest;
import com.sda.lukaapp.services.rest.dto.ServiceMapper;
import com.sda.lukaapp.services.rest.dto.ServiceResponse;
import com.sda.lukaapp.services.rest.dto.UpdateServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.sda.lukaapp.exception.NotFoundException.ErrorCode.SERVICE_NOT_FOUND;

    @org.springframework.stereotype.Service
    public class ServicesService {
        private final Logger logger = LoggerFactory.getLogger(ServicesService.class);
        private final ServiceRepository serviceRepository;
        private final ServiceMapper serviceMapper;
        private final ObjectMapper jacksonObjectMapper;

        @Autowired
        ServicesService(ServiceRepository serviceRepository, ServiceMapper serviceMapper, ObjectMapper jacksonObjectMapper) {
            this.serviceRepository = serviceRepository;
            this.serviceMapper = serviceMapper;
            this.jacksonObjectMapper = jacksonObjectMapper;
        }

        public List<ServiceResponse> findAll() {
            logger.debug("finding all services");
            return serviceRepository.findAll().stream()
                    .map(serviceMapper::toDto)
                    .collect(Collectors.toList());
        }

        public ServiceResponse findById(long id) {
            logger.debug("finding service by id: {}", id);
            return serviceMapper.toDto(serviceRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            SERVICE_NOT_FOUND, "service not found")));
        }

        public ServiceResponse save(CreateServiceRequest createServiceRequest) {
            logger.debug("saving service: {}", createServiceRequest);

            Service service = serviceMapper.toEntity(createServiceRequest);

            Service newService = serviceRepository.save(service);
            // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)
            return serviceMapper.toDto(newService);
        }

        public ServiceResponse update(UpdateServiceRequest request, long id) {
            logger.debug("updating service with id: {} and request body: {}", id, request);
            Service serviceToUpdate = serviceRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            SERVICE_NOT_FOUND, "service not found"));

            serviceMapper.map(request, serviceToUpdate);

            Service updatedService = serviceRepository.save(serviceToUpdate);
            // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)

            return serviceMapper.toDto(updatedService);
        }

        public void delete(long id) {
            logger.debug("deleting user with id: {}", id);
            serviceRepository.deleteById(id);
        }


}
