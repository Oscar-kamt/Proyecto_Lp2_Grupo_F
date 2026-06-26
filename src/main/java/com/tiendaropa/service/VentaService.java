package com.tiendaropa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaropa.model.Venta;
import com.tiendaropa.repository.VentaRepository;
import java.time.LocalDate;

import com.tiendaropa.dto.VentaRequest;
import com.tiendaropa.model.Cliente;
import com.tiendaropa.model.DetalleVenta;
import com.tiendaropa.model.Producto;
import jakarta.servlet.http.HttpSession;
import com.tiendaropa.model.Usuario;

@Service
public class VentaService {


@Autowired
VentaRepository repo;



public List<Venta> listar(){

return repo.findAll();

}

public List<Venta> listarPorUsuario(Usuario usuario){

    return repo.findByUsuario(usuario);

}

public Venta buscar(Integer id){

    return repo.findById(id).orElse(null);

}


public Venta guardar(Venta v){

return repo.save(v);

}

public Integer generarVenta(
        VentaRequest request,
        ClienteService clienteService,
        ProductoService productoService,
        DetalleVentaService detalleVentaService,
        HttpSession session) {

Cliente cliente = clienteService.buscarPorDni(request.getDni());

Venta venta = new Venta();

Usuario usuario = (Usuario) session.getAttribute("usuario");

venta.setCliente(cliente);
venta.setUsuario(usuario);
venta.setFecha(LocalDate.now());
venta.setTotal(request.getTotal());

venta.setNumeroBoleta("BOL-" + System.currentTimeMillis());

venta = repo.save(venta);

for(var item : request.getDetalle()){

Producto producto = productoService.buscar(item.getIdProducto());

producto.setStock(producto.getStock() - item.getCantidad());

productoService.guardar(producto);

DetalleVenta detalle = new DetalleVenta();

detalle.setVenta(venta);
detalle.setProducto(producto);
detalle.setCantidad(item.getCantidad());
detalle.setPrecio(item.getPrecio());
detalle.setSubtotal(item.getSubtotal());

detalleVentaService.guardar(detalle);

}

return venta.getId();

}


}