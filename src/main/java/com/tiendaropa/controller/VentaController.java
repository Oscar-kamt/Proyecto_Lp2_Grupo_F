package com.tiendaropa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tiendaropa.model.Venta;
import com.tiendaropa.service.ClienteService;
import com.tiendaropa.service.ProductoService;
import com.tiendaropa.service.VentaService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	VentaService service;

	@GetMapping
	public String listar(Model model) {

		model.addAttribute("ventas", service.listar());

		return "venta/listado";

	}

	@Autowired
	ClienteService clienteService;

	@Autowired
	ProductoService productoService;

	@GetMapping("/nuevo")
	public String nuevo(Model model) {

		model.addAttribute("venta", new Venta());

		model.addAttribute("clientes", clienteService.listar());

		model.addAttribute("productos", productoService.listar());

		return "venta/nuevo";

	}

	@PostMapping("/guardar")
	public String guardar(Venta v) {

		service.guardar(v);

		return "redirect:/ventas";

	}

}