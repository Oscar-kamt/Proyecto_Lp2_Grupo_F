package com.tiendaropa.dto;

import java.util.List;

public class VentaRequest {

    private String dni;
    private Double total;
    private List<DetalleVentaRequest> detalle;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleVentaRequest> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleVentaRequest> detalle) {
        this.detalle = detalle;
    }

}