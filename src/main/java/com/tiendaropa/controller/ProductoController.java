package com.tiendaropa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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



@Autowired
CategoriaService categoriaService;

private boolean accesoAdmin(HttpSession session){

    Usuario usuario = (Usuario) session.getAttribute("usuario");

    return usuario != null && usuario.getRol().equals("ADMIN");

}

@GetMapping
public String listar(Model model, HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("productos", service.listar());

    return "producto/listado";

}



@GetMapping("/nuevo")
public String nuevo(Model model, HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("producto",new Producto());
    model.addAttribute("categorias", categoriaService.listar());

    return "producto/nuevo";

}


@GetMapping("/editar/{id}")
public String editar(@PathVariable Integer id,
                     Model model,
                     HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("producto", service.buscar(id));

    model.addAttribute("categorias",
            categoriaService.listar());

    return "producto/editar";

}



@PostMapping("/guardar")
public String guardar(Producto p,
                      Model model,
                      HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    Producto existe = service.buscarPorNombre(p.getNombre());

    if(existe != null && !existe.getId().equals(p.getId())){

        model.addAttribute(
                "errorNombre",
                "Este producto ya está registrado.");

        model.addAttribute("producto", p);

        model.addAttribute("categorias",
                categoriaService.listar());

        if(p.getId() == null){

            return "producto/nuevo";

        }

        model.addAttribute(
                "errorGeneral",
                "No se puede duplicar otro producto.");

        return "producto/editar";

    }

    service.guardar(p);

    return "redirect:/productos";

}



@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable Integer id,
                       HttpSession session,
                       RedirectAttributes redirect){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    try{

        service.eliminar(id);

        redirect.addFlashAttribute(
                "mensajeExito",
                "Producto eliminado correctamente.");

    }catch(Exception e){

        redirect.addFlashAttribute(
                "mensajeError",
                "No se puede eliminar el producto porque está siendo utilizado en una o más ventas.");

    }

    return "redirect:/productos";

}

@GetMapping("/buscar")
public String buscar(

@RequestParam String nombre,

Model model,

HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("productos",
            service.buscar(nombre));

    return "producto/listado";

}

}