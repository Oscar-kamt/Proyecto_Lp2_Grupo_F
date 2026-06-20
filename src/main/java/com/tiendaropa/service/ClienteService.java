package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Cliente;
import com.tiendaropa.repository.ClienteRepository;



@Service
public class ClienteService {


@Autowired
ClienteRepository repo;



public List<Cliente> listar(){

return repo.findAll();

}


public Cliente guardar(Cliente c){

return repo.save(c);

}

public List<Cliente> buscar(String nombre){

    return repo.findByNombreContainingIgnoreCase(nombre);

}

}
