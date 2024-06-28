package br.unibh.sdm.backend_estacionamento.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_estacionamento.entidades.ReservaEstacionamento;
import br.unibh.sdm.backend_estacionamento.persistencia.ReservaEstacionamentoRepository;

@Service
public class ReservaEstacionamentoService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ReservaEstacionamentoRepository reservaEstacionamentoRepo;

    public ReservaEstacionamentoService(ReservaEstacionamentoRepository reservaEstacionamentoRepository) {
        this.reservaEstacionamentoRepo = reservaEstacionamentoRepository;
    }

    public List<ReservaEstacionamento> getReservas() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todas as reservas");
        }
        Iterable<ReservaEstacionamento> lista = this.reservaEstacionamentoRepo.findAll();
        if (lista == null) {
            return new ArrayList<ReservaEstacionamento>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public ReservaEstacionamento getReservaById(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando Reserva com o id {}", id);
        }
        Optional<ReservaEstacionamento> retorno = this.reservaEstacionamentoRepo.findById(id);
        if (!retorno.isPresent()) {
            throw new RuntimeException("Reserva com o id " + id + " não encontrada");
        }
        return retorno.get();
    }

    public List<ReservaEstacionamento> getReservaByCodigo(String codigo) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todas as reservas com o código {}", codigo);
        }
        List<ReservaEstacionamento> lista = this.reservaEstacionamentoRepo.findByCodigo(codigo);
        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException("Reserva com o código " + codigo + " não encontrada");
        }
        return lista;
    }

    public ReservaEstacionamento saveReserva(ReservaEstacionamento reservaEstacionamento) {
        if (logger.isInfoEnabled()) {
            logger.info("Salvando Reserva com os detalhes {}", reservaEstacionamento.toString());
        }
        return this.reservaEstacionamentoRepo.save(reservaEstacionamento);
    }

    public void deleteReserva(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Reserva com id {}", id);
        }
        this.reservaEstacionamentoRepo.deleteById(id);
    }

    public boolean isReservaExists(ReservaEstacionamento reservaEstacionamento) {
        Optional<ReservaEstacionamento> retorno = this.reservaEstacionamentoRepo.findById(reservaEstacionamento.getId());
        return retorno.isPresent();
    }

    public boolean isReservaExists(String id) {
        Optional<ReservaEstacionamento> retorno = this.reservaEstacionamentoRepo.findById(id);
        return retorno.isPresent();
    }
}
