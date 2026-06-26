package com.tiendaropa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tiendaropa.model.Usuario;
import com.tiendaropa.service.VentaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReporteVentaController {

    @Autowired
    VentaService ventaService;

    @GetMapping("/reporteVentas")
    public String reporte(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        model.addAttribute("usuario", usuario);

        if(usuario.getRol().equals("ADMIN")){

            model.addAttribute("ventas", ventaService.listar());

        }else{

            model.addAttribute("ventas", ventaService.listarPorUsuario(usuario));

        }

        return "venta/reporte";

    }

}