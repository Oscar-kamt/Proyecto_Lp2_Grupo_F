package com.tiendaropa.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numeroBoleta;

    private LocalDate fecha;

    private double total;

    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

}