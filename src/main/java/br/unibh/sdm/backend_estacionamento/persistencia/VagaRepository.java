package br.unibh.sdm.backend_estacionamento.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_estacionamento.entidades.Vaga;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface VagaRepository extends CrudRepository<Vaga, String> {
	
	List<Vaga> findByCodigo(String codigo);
	
}
