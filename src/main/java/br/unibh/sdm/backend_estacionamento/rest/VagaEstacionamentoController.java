package br.unibh.sdm.backend_estacionamento.rest;

import java.util.List;

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

import br.unibh.sdm.backend_estacionamento.entidades.VagaEstacionamento;
import br.unibh.sdm.backend_estacionamento.negocio.VagaEstacionamentoService;
import jakarta.validation.constraints.NotNull;

/**
 * Classe contendo as definições de serviços REST/JSON para Vaga de Estacionamento
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "vaga")
public class VagaEstacionamentoController {
   
    private final VagaEstacionamentoService vagaEstacionamentoService;

    public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService){
        this.vagaEstacionamentoService = vagaEstacionamentoService;
    }

    @GetMapping
    public List<VagaEstacionamento> getVagas(){
        return vagaEstacionamentoService.getVagas();
    }
    
    @GetMapping(value="{id}")
    public VagaEstacionamento getVagaById(@PathVariable String id) throws Exception {
        if(!ObjectUtils.isEmpty(id)){
           return vagaEstacionamentoService.getVagaById(id);
        }
        throw new Exception("Vaga com código "+id+" não encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VagaEstacionamento createVaga(@RequestBody @NotNull VagaEstacionamento vagaEstacionamento) throws Exception {
         return vagaEstacionamentoService.saveVaga(vagaEstacionamento);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VagaEstacionamento updateVaga(@PathVariable String id, 
    		@RequestBody @NotNull VagaEstacionamento vagaEstacionamento) throws Exception {
    	if (!id.equals(vagaEstacionamento.getCodigo())) {
    		throw new Exception("Código "+id+" não está correto");
    	}
    	if (!vagaEstacionamentoService.isVagaExists(vagaEstacionamento)) {
    		throw new Exception("Vaga com código "+id+" não existe");
    	}
        return vagaEstacionamentoService.saveVaga(vagaEstacionamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteVaga(@PathVariable String id) throws Exception {
    	if (!vagaEstacionamentoService.isVagaExists(id)) {
    		throw new Exception("Vaga com código "+id+" não existe");
    	} 
    	vagaEstacionamentoService.deleteVaga(id);
        return true;
    }
    
}
