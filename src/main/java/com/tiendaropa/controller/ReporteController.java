package com.tiendaropa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiendaropa.report.ReporteService;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/boleta/{id}")
    public ResponseEntity<byte[]> generarBoleta(
            @PathVariable Integer id) throws Exception {

        byte[] pdf = reporteService.generarBoleta(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_PDF);

        headers.setContentDisposition(
                ContentDisposition.inline()
                        .filename("Boleta.pdf")
                        .build());

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdf);

    }

}