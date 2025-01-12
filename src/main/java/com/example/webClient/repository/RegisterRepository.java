package com.example.webClient.repository;


import com.example.webClient.model.RegisterCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterCustomer, Integer> {
}
