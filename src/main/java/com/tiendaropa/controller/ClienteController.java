package com.tiendaropa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import com.tiendaropa.model.Usuario;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tiendaropa.model.Cliente;
import com.tiendaropa.service.ClienteService;



@Controller
@RequestMapping("/clientes")
public class ClienteController {



@Autowired
ClienteService service;

private boolean accesoAdmin(HttpSession session){

    Usuario usuario = (Usuario) session.getAttribute("usuario");

    return usuario != null && usuario.getRol().equals("ADMIN");

}


@GetMapping
public String listar(Model model, HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("clientes", service.listar());

    return "cliente/listado";

}


@GetMapping("/nuevo")
public String nuevo(Model model, HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("cliente", new Cliente());

    return "cliente/nuevo";

}

@PostMapping("/guardar")
public String guardar(Cliente c,
                      Model model,
                      HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    boolean hayError = false;

    Cliente existeDni = service.buscarPorDni(c.getDni());

    if(existeDni != null && !existeDni.getId().equals(c.getId())){

        model.addAttribute("errorDni", "Este DNI ya está registrado.");
        hayError = true;

    }

    Cliente existeTelefono = service.buscarPorTelefono(c.getTelefono());

    if(existeTelefono != null && !existeTelefono.getId().equals(c.getId())){

        model.addAttribute("errorTelefono", "Este teléfono ya está registrado.");
        hayError = true;

    }

    Cliente existeCorreo = service.buscarPorCorreo(c.getCorreo());

    if(existeCorreo != null && !existeCorreo.getId().equals(c.getId())){

        model.addAttribute("errorCorreo", "Este correo ya está registrado.");
        hayError = true;

    }

    if(hayError){

        model.addAttribute("cliente", c);

        if(c.getId() == null){

            return "cliente/nuevo";

        }
        
        model.addAttribute("errorGeneral",
                "No se puede duplicar la información de otro cliente.");

        return "cliente/editar";

    }

    service.guardar(c);

    return "redirect:/clientes";

}

@GetMapping("/editar/{id}")
public String editar(@PathVariable Integer id,
                     Model model,
                     HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("cliente", service.buscar(id));

    return "cliente/editar";

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
                "Cliente eliminado correctamente.");

    }catch(Exception e){

        redirect.addFlashAttribute(
                "mensajeError",
                "No se puede eliminar el cliente porque tiene ventas registradas.");

    }

    return "redirect:/clientes";

}

@GetMapping("/buscar")
public String buscar(

        @RequestParam("dni") String dni,

        Model model,

        HttpSession session){

    if(!accesoAdmin(session)){
        return "redirect:/";
    }

    model.addAttribute("clientes",
            service.buscar(dni));

    return "cliente/listado";

}


}
