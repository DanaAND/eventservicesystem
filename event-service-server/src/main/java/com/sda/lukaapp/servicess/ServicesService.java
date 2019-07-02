package com.sda.lukaapp.servicess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.exception.NotFoundException;
import com.sda.lukaapp.servicess.domain.Services;
import com.sda.lukaapp.repository.ServiceRepository;
import com.sda.lukaapp.servicess.rest.dto.CreateServiceRequest;
import com.sda.lukaapp.servicess.rest.dto.ServiceMapper;
import com.sda.lukaapp.servicess.rest.dto.ServiceResponse;
import com.sda.lukaapp.servicess.rest.dto.UpdateServiceRequest;
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
            logger.debug("finding all servicess");
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
            logger.debug("saving services: {}", createServiceRequest);

            Services services = serviceMapper.toEntity(createServiceRequest);

            Services newServices = serviceRepository.save(services);
            // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)
            return serviceMapper.toDto(newServices);
        }

        public ServiceResponse update(UpdateServiceRequest request, long id) {
            logger.debug("updating service with id: {} and request body: {}", id, request);
            Services servicesToUpdate = serviceRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            SERVICE_NOT_FOUND, "service not found"));

            serviceMapper.map(request, servicesToUpdate);

            Services updatedServices = serviceRepository.save(servicesToUpdate);
            // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)

            return serviceMapper.toDto(updatedServices);
        }

        public void delete(long id) {
            logger.debug("deleting user with id: {}", id);
            serviceRepository.deleteById(id);
        }


}
