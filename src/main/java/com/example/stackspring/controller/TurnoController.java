package com.example.stackspring.controller;

import com.example.stackspring.model.Turno;
import com.example.stackspring.model.TurnoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.LinkedList;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private int contador = 0;
    private Queue<Turno> cola = new LinkedList<>();

    @PostMapping
    public Turno pedirTurno(@RequestBody TurnoRequest request) {
        contador++;
        Turno turno = new Turno(contador, request.getNombre());
        cola.offer(turno);
        return turno;
    }

    @GetMapping("/siguiente")
    public Turno siguiente() {
        return cola.poll();
    }

    @GetMapping
    public Queue<Turno> verCola() {
        return cola;
    }
}