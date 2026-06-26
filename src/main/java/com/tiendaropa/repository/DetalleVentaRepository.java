package com.tiendaropa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.tiendaropa.model.Venta;
import com.tiendaropa.model.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Integer>{

    List<DetalleVenta> findByVenta(Venta venta);

}