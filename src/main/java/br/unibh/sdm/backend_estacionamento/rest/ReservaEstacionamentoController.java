package br.unibh.sdm.backend_estacionamento.rest;

import br.unibh.sdm.backend_estacionamento.negocio.ReservaEstacionamentoService;
import br.unibh.sdm.backend_estacionamento.entidades.ReservaEstacionamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Classe contendo as definições de serviços REST/JSON para Reserva de Estacionamento
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "reserva")
public class ReservaEstacionamentoController {
   
    private final ReservaEstacionamentoService reservaEstacionamentoService;

    public ReservaEstacionamentoController(ReservaEstacionamentoService reservaEstacionamentoService) {
        this.reservaEstacionamentoService = reservaEstacionamentoService;
    }

    @GetMapping
    public List<ReservaEstacionamento> getReservas() {
        return reservaEstacionamentoService.getReservas();
    }
    
    @GetMapping(value="{id}")
    public ReservaEstacionamento getReservaById(@PathVariable String id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return reservaEstacionamentoService.getReservaById(id);
        }
        throw new Exception("Reserva com código " + id + " não encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservaEstacionamento createReserva(@RequestBody @NotNull ReservaEstacionamento reservaEstacionamento) {
        return reservaEstacionamentoService.saveReserva(reservaEstacionamento);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservaEstacionamento updateReserva(@PathVariable String id, @RequestBody @NotNull ReservaEstacionamento reservaEstacionamento) throws Exception {
        if (!id.equals(reservaEstacionamento.getId())) {
            throw new Exception("Código " + id + " não está correto");
        }
        if (!reservaEstacionamentoService.isReservaExists(id)) {
            throw new Exception("Reserva com código " + id + " não existe");
        }
        return reservaEstacionamentoService.saveReserva(reservaEstacionamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteReserva(@PathVariable String id) throws Exception {
        if (!reservaEstacionamentoService.isReservaExists(id)) {
            throw new Exception("Reserva com código " + id + " não existe");
        }
        reservaEstacionamentoService.deleteReserva(id);
        return true;
    }
}
