package com.tiendaropa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.tiendaropa.model.Producto;
import com.tiendaropa.service.CategoriaService;
import com.tiendaropa.service.ProductoService;

import jakarta.servlet.http.HttpSession;
import com.tiendaropa.model.Usuario;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
public class ProductoController {



@Autowired
ProductoService service;



@GetMapping
public String listar(Model model){

model.addAttribute("productos",service.listar());

return "producto/listado";

}


@Autowired
CategoriaService categoriaService;

@GetMapping("/nuevo")
public String nuevo(Model model){

model.addAttribute("producto",new Producto());
model.addAttribute("categorias", categoriaService.listar());

return "producto/nuevo";

}

@GetMapping("/editar/{id}")
public String editar(@PathVariable Integer id, Model model){

    model.addAttribute("producto", service.buscar(id));

    model.addAttribute("categorias",
            categoriaService.listar());

    return "producto/editar";

}



@PostMapping("/guardar")
public String guardar(Producto p){

service.guardar(p);

return "redirect:/productos";

}



@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable Integer id){

service.eliminar(id);

return "redirect:/productos";

}

@GetMapping("/buscar")
public String buscar(

@RequestParam String nombre,

Model model){


model.addAttribute("productos",
service.buscar(nombre));


return "producto/listado";


}

}