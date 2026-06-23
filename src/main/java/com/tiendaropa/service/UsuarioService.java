package com.tiendaropa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Usuario;
import com.tiendaropa.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repo;

    public Usuario login(String usuario,String clave){

        return repo.findByUsuarioAndClave(usuario,clave);

    }

    public Usuario guardar(Usuario usuario){

        return repo.save(usuario);

    }

    public Usuario buscarUsuario(String usuario){

        return repo.findByUsuario(usuario);

    }

}