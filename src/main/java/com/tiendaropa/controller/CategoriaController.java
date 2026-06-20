package com.tiendaropa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.tiendaropa.model.Categoria;
import com.tiendaropa.service.CategoriaService;



@Controller
@RequestMapping("/categorias")
public class CategoriaController {



@Autowired
CategoriaService service;



@GetMapping
public String listar(Model model){

model.addAttribute("categorias",service.listar());

return "categoria/listado";

}



@GetMapping("/nuevo")
public String nuevo(Model model){

model.addAttribute("categoria",new Categoria());

return "categoria/nuevo";

}



@PostMapping("/guardar")
public String guardar(Categoria c){

service.guardar(c);

return "redirect:/categorias";

}


}