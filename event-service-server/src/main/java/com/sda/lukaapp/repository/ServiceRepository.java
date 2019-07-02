package com.sda.lukaapp.repository;

import com.sda.lukaapp.servicess.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}


