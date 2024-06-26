package br.unibh.sdm.backend_estacionamento.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_estacionamento.entidades.VagaEstacionamento;
import br.unibh.sdm.backend_estacionamento.persistencia.VagaEstacionamentoRepository;

/**
 * Classe contendo a lógica de negócio para VagaEstacionamento
 */
@Service
public class VagaEstacionamentoService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final VagaEstacionamentoRepository vagaEstacionamentoRepo;

    public VagaEstacionamentoService(VagaEstacionamentoRepository vagaEstacionamentoRepository){
        this.vagaEstacionamentoRepo = vagaEstacionamentoRepository;
    }
    
    public List<VagaEstacionamento> getVagas(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todas as vagas");
        }
        Iterable<VagaEstacionamento> lista = this.vagaEstacionamentoRepo.findAll();
        if (lista == null) {
        	return new ArrayList<VagaEstacionamento>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public VagaEstacionamento getVagaById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Vaga com o id {}", id);
        }
        Optional<VagaEstacionamento> retorno = this.vagaEstacionamentoRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Vaga com o id "+id+" não encontrada");
        }
        return retorno.get();
    }
    
    public VagaEstacionamento saveVaga(VagaEstacionamento vagaEstacionamento){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Vaga com os detalhes {}", vagaEstacionamento.toString());
        }
        return this.vagaEstacionamentoRepo.save(vagaEstacionamento);
    }
    
    public void deleteVaga(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Vaga com id {}", id);
        }
        this.vagaEstacionamentoRepo.deleteById(id);
    }

    public boolean isVagaExists(VagaEstacionamento vagaEstacionamento){
    	Optional<VagaEstacionamento> retorno = this.vagaEstacionamentoRepo.findById(vagaEstacionamento.getCodigo());
        return retorno.isPresent();
    }

    public boolean isVagaExists(String id){
    	Optional<VagaEstacionamento> retorno = this.vagaEstacionamentoRepo.findById(id);
        return retorno.isPresent();
    }
}