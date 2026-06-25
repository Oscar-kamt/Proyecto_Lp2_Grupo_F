package com.tiendaropa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaropa.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

	   List<Cliente> findByDniContainingIgnoreCase(String dni);
}
