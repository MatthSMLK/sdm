package br.unibh.sdm.backend_estacionamento.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.unibh.sdm.backend_estacionamento.entidades.Estacionamento;
import br.unibh.sdm.backend_estacionamento.negocio.EstacionamentoService;

import java.util.Optional;

@RestController
@RequestMapping("/api/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService service;

    @PostMapping
    public ResponseEntity<Estacionamento> criarEstacionamento(@RequestBody Estacionamento estacionamento) {
        Estacionamento estacionamentoSalvo = service.salvar(estacionamento);
        return ResponseEntity.ok(estacionamentoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estacionamento> buscarEstacionamentoPorId(@PathVariable String id) {
        Optional<Estacionamento> estacionamento = service.buscarPorId(id);
        if (estacionamento.isPresent()) {
            return ResponseEntity.ok(estacionamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Estacionamento>> buscarTodosEstacionamentos() {
        Iterable<Estacionamento> estacionamentos = service.buscarTodos();
        return ResponseEntity.ok(estacionamentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstacionamento(@PathVariable String id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
