package br.unibh.sdm.backend_estacionamento.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_estacionamento.entidades.Estacionamento;
import br.unibh.sdm.backend_estacionamento.persistencia.EstacionamentoRepository;

import java.util.Optional;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository repository;

    public Estacionamento salvar(Estacionamento estacionamento) {
        return repository.save(estacionamento);
    }

    public Optional<Estacionamento> buscarPorId(String id) {
        return repository.findById(id);
    }

    public Iterable<Estacionamento> buscarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(String id) {
        repository.deleteById(id);
    }
}
