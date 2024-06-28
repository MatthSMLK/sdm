package br.unibh.sdm.backend_estacionamento.rest;

import br.unibh.sdm.backend_estacionamento.negocio.VagaEstacionamentoService;
import br.unibh.sdm.backend_estacionamento.entidades.VagaEstacionamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Classe contendo as definições de serviços REST/JSON para Vaga de Estacionamento
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "vaga")
public class VagaEstacionamentoController {
   
    private final VagaEstacionamentoService vagaEstacionamentoService;

    public VagaEstacionamentoController(VagaEstacionamentoService vagaEstacionamentoService) {
        this.vagaEstacionamentoService = vagaEstacionamentoService;
    }

    @GetMapping
    public List<VagaEstacionamento> getVagas() {
        return vagaEstacionamentoService.getVagas();
    }
    
    @GetMapping(value="{id}")
    public VagaEstacionamento getVagaById(@PathVariable String id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return vagaEstacionamentoService.getVagaById(id);
        }
        throw new Exception("Vaga com código " + id + " não encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public VagaEstacionamento createVaga(@RequestBody @NotNull VagaEstacionamento vagaEstacionamento) {
        return vagaEstacionamentoService.saveVaga(vagaEstacionamento);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VagaEstacionamento updateVaga(@PathVariable String id, @RequestBody @NotNull VagaEstacionamento vagaEstacionamento) throws Exception {
        if (!id.equals(vagaEstacionamento.getId())) {
            throw new Exception("Código " + id + " não está correto");
        }
        if (!vagaEstacionamentoService.isVagaExists(id)) {
            throw new Exception("Vaga com código " + id + " não existe");
        }
        return vagaEstacionamentoService.saveVaga(vagaEstacionamento);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean deleteVaga(@PathVariable String id) throws Exception {
        if (!vagaEstacionamentoService.isVagaExists(id)) {
            throw new Exception("Vaga com código " + id + " não existe");
        }
        vagaEstacionamentoService.deleteVaga(id);
        return true;
    }
}
