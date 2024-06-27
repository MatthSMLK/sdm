package br.unibh.sdm.backend_estacionamento.rest;

import br.unibh.sdm.backend_estacionamento.negocio.ReservaEstacionamentoService;
import java.util.List;
import br.unibh.sdm.backend_estacionamento.entidades.ReservaEstacionamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.constraints.NotNull;

/**
 * Classe contendo as definições de serviços REST/JSON para Reserva de Estacionamento
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "reserva")
public class ReservaEstacionamentoController {
   
    private final ReservaEstacionamentoService reservaEstacionamentoService;

    public ReservaEstacionamentoController(ReservaEstacionamentoService reservaEstacionamentoService){
        this.reservaEstacionamentoService = reservaEstacionamentoService;
    }

    @GetMapping(value = "")
    public List<ReservaEstacionamentoService> getReservas(){
        return reservaEstacionamentoService.getReservas();
    }
    
    @GetMapping(value="{id}")
    public ReservaEstacionamentoService getReservaById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return reservaEstacionamentoService.getReservaById(id);
        }
        throw new Exception("Reserva com código "+id+" não encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservaEstacionamento createReserva(@RequestBody @NotNull ReservaEstacionamento reservaEstacionamento) throws Exception {
         return reservaEstacionamentoService.saveReserva(reservaEstacionamento);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReservaEstacionamento updateReserva(@PathVariable String id, 
    		@RequestBody @NotNull ReservaEstacionamento reservaEstacionamento) throws Exception {
         return reservaEstacionamentoService.saveReserva(reservaEstacionamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteReserva(@PathVariable String id) throws Exception {
         reservaEstacionamentoService.deleteReserva(id);
         return true;
    }
    
}
