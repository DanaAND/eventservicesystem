package com.sda.lukaapp.location.rest.dto;

import com.sda.lukaapp.location.domain.Location;
import org.springframework.stereotype.Component;


@Component
public class LocationMapper {

    //dto to entity conversion

    public Location toEntity(CreateLocationRequest createLocationRequest){
        Location location = new Location();
        location.setAdress(createLocationRequest.getAdress());
        location.setCity(createLocationRequest.getCity());
        return location;
    }

    //entity to dto conversion

  public LocationResponse toDto(Location location){
        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setCity(location.getCity());
        locationResponse.setAdress(location.getAdress());
        return locationResponse;
  }

  //mapping for partial update

    public void map(UpdateLocationRequest request,Location location ){
        if (request.getCity() != null) {
            location.setCity(request.getCity());
        }
        if(request.getAdress() !=null){
            location.setAdress(request.getAdress());
        }
    }
}
