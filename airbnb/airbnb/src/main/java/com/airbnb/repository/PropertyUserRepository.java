package com.airbnb.repository;

import com.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {
    @Query("select p from PropertyUser p where p.userName = ?1")
    Optional<PropertyUser> findByUserName(String userName);


}