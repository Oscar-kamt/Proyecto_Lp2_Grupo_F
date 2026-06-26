package com.tiendaropa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.tiendaropa.model.Producto;
import com.tiendaropa.model.Venta;
import com.tiendaropa.service.ClienteService;
import com.tiendaropa.service.ProductoService;
import com.tiendaropa.service.VentaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.tiendaropa.service.DetalleVentaService;
import com.tiendaropa.dto.VentaRequest;

@Controller
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	VentaService service;

	@GetMapping
	public String formulario(Model model) {

	    model.addAttribute("venta", new Venta());

	    model.addAttribute("clientes", clienteService.listar());

	    model.addAttribute("productos", productoService.listar());

	    return "venta/formulario";

	}

	@Autowired
	ClienteService clienteService;

	@Autowired
	ProductoService productoService;
	
	@Autowired
	DetalleVentaService detalleVentaService;

	
	@PostMapping("/guardar")
	@ResponseBody
	public ResponseEntity<Integer> guardar(
	        @RequestBody VentaRequest request,
	        HttpSession session) {

	    Integer idVenta = service.generarVenta(
	            request,
	            clienteService,
	            productoService,
	            detalleVentaService,
	            session
	    );

	    return new ResponseEntity<>(idVenta, HttpStatus.OK);

	}

	@GetMapping("/buscarCliente")
	@ResponseBody
	public Object buscarCliente(@RequestParam String dni){

	    var cliente = clienteService.buscarPorDni(dni);

	    if(cliente == null){
	        return "";
	    }

	    return cliente;

	}


	@GetMapping("/buscarProducto")
	@ResponseBody
	public Producto buscarProducto(@RequestParam Integer id){
	    return productoService.buscar(id);

	}
	
	@GetMapping("/boleta/{id}")
	public String verBoleta(@PathVariable Integer id, Model model){

	    Venta venta = service.buscar(id);

	    model.addAttribute("venta", venta);
	    model.addAttribute("detalle", detalleVentaService.buscarPorVenta(venta));

	    return "venta/boleta";

	}
}