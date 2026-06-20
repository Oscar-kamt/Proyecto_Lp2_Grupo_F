package com.tiendaropa.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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



    private LocalDate fecha;



    private double total;



    private int cantidad;



    @ManyToOne
    private Cliente cliente;



    @ManyToOne
    private Producto producto;



}