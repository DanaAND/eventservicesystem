package com.sda.lukaapp.servicess.rest;


import com.sda.lukaapp.servicess.ServicesService;
import com.sda.lukaapp.servicess.rest.dto.CreateServiceRequest;
import com.sda.lukaapp.servicess.rest.dto.ServiceResponse;
import com.sda.lukaapp.servicess.rest.dto.UpdateServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ServiceController.API_SERVICES)
public class ServiceController {

    public static final String API_SERVICES = "/servicess";

    private ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Autowired


    @GetMapping
    public List<ServiceResponse> findAll() {
        return servicesService.findAll();
    }


    @PostMapping
    public ServiceResponse create(@RequestBody final CreateServiceRequest createAccountRequest) {
        return servicesService.save(createAccountRequest);
    }

    @PutMapping("/{id}")
    public ServiceResponse update(@RequestBody final UpdateServiceRequest updateServiceRequest,
                                  @PathVariable("id") final long id) {
        return servicesService.update(updateServiceRequest, id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long id) {
        servicesService.delete(id);
    }
}




