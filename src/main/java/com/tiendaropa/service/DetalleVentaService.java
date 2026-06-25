package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.DetalleVenta;
import com.tiendaropa.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

    @Autowired
    DetalleVentaRepository repo;

    public List<DetalleVenta> listar(){

        return repo.findAll();

    }

    public DetalleVenta guardar(DetalleVenta detalle){

        return repo.save(detalle);

    }

}