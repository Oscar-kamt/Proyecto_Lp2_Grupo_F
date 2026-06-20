package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Categoria;
import com.tiendaropa.repository.CategoriaRepository;



@Service
public class CategoriaService {


@Autowired
CategoriaRepository repo;



public List<Categoria> listar(){

return repo.findAll();

}


public Categoria guardar(Categoria c){

return repo.save(c);

}


}