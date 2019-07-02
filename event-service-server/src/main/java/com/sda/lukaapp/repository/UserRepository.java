package com.sda.lukaapp.repository;

import com.sda.lukaapp.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
