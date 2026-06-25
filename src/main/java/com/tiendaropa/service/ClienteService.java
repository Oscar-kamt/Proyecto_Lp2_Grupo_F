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

public List<Cliente> buscar(String dni){

    return repo.findByDniContainingIgnoreCase(dni);

}

public Cliente buscarPorDni(String dni){

    return repo.findByDni(dni);

}

public Cliente buscarPorTelefono(String telefono){

    return repo.findByTelefono(telefono);

}

public Cliente buscarPorCorreo(String correo){

    return repo.findByCorreo(correo);

}

}
