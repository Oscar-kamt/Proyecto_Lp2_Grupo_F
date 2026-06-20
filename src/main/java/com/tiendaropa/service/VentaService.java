package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Venta;
import com.tiendaropa.repository.VentaRepository;



@Service
public class VentaService {


@Autowired
VentaRepository repo;



public List<Venta> listar(){

return repo.findAll();

}



public Venta guardar(Venta v){

return repo.save(v);

}


}