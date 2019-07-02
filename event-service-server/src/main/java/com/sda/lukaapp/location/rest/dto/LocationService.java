package com.sda.lukaapp.location.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sda.lukaapp.exception.NotFoundException;
import com.sda.lukaapp.location.domain.Location;
import com.sda.lukaapp.repository.LocationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sda.lukaapp.exception.NotFoundException.ErrorCode.LOCATION_NOT_FOUND;


@Service
public class LocationService {

    private final Logger logger = LoggerFactory.getLogger(LocationService.class);
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper, ObjectMapper jacksonObjectMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    public List<LocationResponse> findAll() {
        logger.debug("finding all locations");
        return locationRepository.findAll().stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationResponse findById(long id) {
        logger.debug("finding location by id: {}", id);
        return locationMapper.toDto(locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        LOCATION_NOT_FOUND, "location not found")));
    }

    public LocationResponse save(CreateLocationRequest createLocationRequest) {
        logger.debug("saving location: {}", createLocationRequest);
        Location location = locationMapper.toEntity(createLocationRequest);
        Location newLocation = locationRepository.save(location);
        return locationMapper.toDto(newLocation);
    }

    public LocationResponse update(UpdateLocationRequest request, long id) {
        logger.debug("updating location with id: {} and request body: {}", id, request);
        Location locationToUpdate = locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        LOCATION_NOT_FOUND, "location not found"));
        locationMapper.map(request, locationToUpdate);
        Location updatedLocation = locationRepository.save(locationToUpdate);
        return locationMapper.toDto(updatedLocation);
    }

    public LocationResponse partialUpdate(Map<String, Object> updates, long id) {
        logger.debug("patching location with id: {} and request body: {}", id, updates);

        try {
            Location user = locationRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            LOCATION_NOT_FOUND, "location not found"));
            jacksonObjectMapper.readerForUpdating(user)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));
            Location updatedLocation = locationRepository.save(user);
            return locationMapper.toDto(updatedLocation);
        } catch (Exception e) {
            logger.error("failed to update location", e);
        }
        return null;
    }

    public void delete(long id) {
        logger.debug("deleting location with id: {}", id);
        locationRepository.deleteById(id);
    }
}