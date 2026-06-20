package com.tiendaropa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.tiendaropa.model.Cliente;
import com.tiendaropa.service.ClienteService;



@Controller
@RequestMapping("/clientes")
public class ClienteController {



@Autowired
ClienteService service;




@GetMapping
public String listar(Model model){


    model.addAttribute("clientes",
            service.listar());


    return "cliente/listado";

}






@GetMapping("/nuevo")
public String nuevo(Model model){


    model.addAttribute("cliente",
            new Cliente());


    return "cliente/nuevo";

}






@PostMapping("/guardar")
public String guardar(Cliente c){


    service.guardar(c);


    return "redirect:/clientes";

}





@GetMapping("/buscar")
public String buscar(

        @RequestParam("nombre") String nombre, Model model){



    model.addAttribute("clientes",
            service.buscar(nombre));



    return "cliente/listado";

}





}
