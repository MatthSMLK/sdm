package br.unibh.sdm.backend_estacionamento.negocio;

import br.unibh.sdm.backend_estacionamento.entidades.ReservaEstacionamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaEstacionamentoService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaEstacionamentoService.class);

    private final ReservaEstacionamentoRepository reservaEstacionamentoRepo;

    public ReservaEstacionamentoService(ReservaEstacionamentoRepository reservaEstacionamentoRepository) {
        this.reservaEstacionamentoRepo = reservaEstacionamentoRepository;
    }

    public List<ReservaEstacionamento> getReservas() {
        logger.info("Buscando todas as reservas");
        return reservaEstacionamentoRepo.findAll();
    }

    public ReservaEstacionamento getReservaById(String id) {
        logger.info("Buscando Reserva com o id {}", id);
        Optional<ReservaEstacionamento> retorno = reservaEstacionamentoRepo.findById(id);
        return retorno.orElseThrow(() -> new RuntimeException("Reserva com o id " + id + " não encontrada"));
    }

    public List<ReservaEstacionamento> getReservaByCodigo(String codigo) {
        logger.info("Buscando todas as reservas com o código {}", codigo);
        return reservaEstacionamentoRepo.findByCodigo(codigo);
    }

    public ReservaEstacionamento saveReserva(ReservaEstacionamento reservaEstacionamento) {
        logger.info("Salvando Reserva com os detalhes {}", reservaEstacionamento);
        return reservaEstacionamentoRepo.save(reservaEstacionamento);
    }

    public void deleteReserva(String id) {
        logger.info("Excluindo Reserva com id {}", id);
        reservaEstacionamentoRepo.deleteById(id);
    }

    public boolean isReservaExists(String id) {
        logger.info("Verificando se existe Reserva com id {}", id);
        return reservaEstacionamentoRepo.existsById(id);
    }
}
