package com.sda.lukaapp.repository;

import com.sda.lukaapp.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
