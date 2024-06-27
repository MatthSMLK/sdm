package br.unibh.sdm.backend_estacionamento.persistencia;

import java.util.List;
import java.util.UUID;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_estacionamento.entidades.Estacionamento;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface EstacionamentoRepository extends CrudRepository<Estacionamento, UUID> {
	
	List<Estacionamento> findByNome(String nome);
	
}
