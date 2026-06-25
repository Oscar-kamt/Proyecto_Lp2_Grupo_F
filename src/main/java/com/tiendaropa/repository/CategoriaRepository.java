package com.tiendaropa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendaropa.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

    List<Categoria> findByNombreContaining(String nombre);

}