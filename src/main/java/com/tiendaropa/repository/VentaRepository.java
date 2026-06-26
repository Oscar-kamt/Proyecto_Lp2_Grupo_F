package com.tiendaropa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaropa.model.Usuario;
import com.tiendaropa.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer>{

    List<Venta> findByUsuario(Usuario usuario);

}