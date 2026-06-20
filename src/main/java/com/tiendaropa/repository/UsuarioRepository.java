package com.tiendaropa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendaropa.model.Usuario;



public interface UsuarioRepository 
extends JpaRepository<Usuario,Integer>{


Usuario findByUsuarioAndClave(String usuario,String clave);


}