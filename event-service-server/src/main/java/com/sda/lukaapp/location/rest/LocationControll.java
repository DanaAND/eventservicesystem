package com.sda.lukaapp.location.rest;

import com.sda.lukaapp.location.rest.dto.CreateLocationRequest;
import com.sda.lukaapp.location.rest.dto.LocationResponse;
import com.sda.lukaapp.location.rest.dto.LocationService;
import com.sda.lukaapp.location.rest.dto.UpdateLocationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationControll {


    private LocationService locationService;

    @Autowired
    public LocationControll(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationResponse> findAll() {
        return locationService.findAll();
    }

    //Nu inteleg cum deosebim id ul locatiei de cel al userului
    @GetMapping("/{id}")
    public LocationResponse findById(@PathVariable("id") final long id) {
        return locationService.findById(id);
    }

    @PostMapping
    public LocationResponse create(@RequestBody final CreateLocationRequest createLocationRequest) {
        return locationService.save(createLocationRequest);
    }

    @PutMapping("/{id}")
    public LocationResponse update(@RequestBody final UpdateLocationRequest updateLocationRequest,
                                   @PathVariable("id") final long id) {
        return locationService.update(updateLocationRequest, id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LocationResponse patch(@RequestBody final Map<String, Object> updates,
                              @PathVariable("id") final long id) {
        return locationService.partialUpdate(updates, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long id) {
        locationService.delete(id);
    }
}