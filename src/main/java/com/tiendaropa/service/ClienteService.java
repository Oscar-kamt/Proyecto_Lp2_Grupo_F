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

public Cliente buscar(Integer id){

    return repo.findById(id).orElse(null);

}

public void eliminar(Integer id){

    repo.deleteById(id);

}

public List<Cliente> buscar(String nombre){

    return repo.findByNombreContainingIgnoreCase(nombre);

}

}
