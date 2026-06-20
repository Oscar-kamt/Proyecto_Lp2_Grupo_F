package com.tiendaropa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaropa.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer>{

}
