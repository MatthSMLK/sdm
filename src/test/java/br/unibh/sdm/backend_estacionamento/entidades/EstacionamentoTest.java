package br.unibh.sdm.backend_estacionamento.entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, EstacionamentoTest.DynamoDBConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EstacionamentoTest {

    @Configuration
    static class DynamoDBConfig {
        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                .build();
        }

        @Bean
        public DynamoDBMapper dynamoDBMapper() {
            return new DynamoDBMapper(amazonDynamoDB());
        }
    }

    private Estacionamento estacionamento;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Central", "Centro");
    }

    @Test
    public void testAdicionarVaga() {
        Calendar c = Calendar.getInstance();
        VagaEstacionamento vaga = new VagaEstacionamento("1", "a1", true, c.getTime());
        estacionamento.adicionarVaga(vaga);
        List<VagaEstacionamento> vagas = estacionamento.getVagas();
        assertEquals(1, vagas.size());
        assertEquals(vaga, vagas.get(0));
    }

    @Test
    public void testRemoverVaga() {
        Calendar c = Calendar.getInstance();
        VagaEstacionamento vaga1 = new VagaEstacionamento("1", "a1", true, c.getTime());
        VagaEstacionamento vaga2 = new VagaEstacionamento("2", "a2", true, c.getTime());
        estacionamento.adicionarVaga(vaga1);
        estacionamento.adicionarVaga(vaga2);
        estacionamento.removerVaga(vaga1);
        List<VagaEstacionamento> vagas = estacionamento.getVagas();
        assertEquals(1, vagas.size());
        assertEquals(vaga2, vagas.get(0));
    }

    @Test
    public void testVerificarVagasDisponiveis() {
        Calendar c = Calendar.getInstance();
        VagaEstacionamento vaga1 = new VagaEstacionamento("1", "a1", true, c.getTime());
        VagaEstacionamento vaga2 = new VagaEstacionamento("2", "a2", false, c.getTime());
        estacionamento.adicionarVaga(vaga1);
        estacionamento.adicionarVaga(vaga2);
        List<VagaEstacionamento> vagasDisponiveis = estacionamento.verificarVagasDisponiveis();
        assertEquals(1, vagasDisponiveis.size());
        assertEquals(vaga2, vagasDisponiveis.get(0));
    }

    @Test
    public void testGettersSetters() {
        estacionamento.setNome("Novo Estacionamento");
        estacionamento.setLocalizacao("Novo Centro");
        assertEquals("Novo Estacionamento", estacionamento.getNome());
        assertEquals("Novo Centro", estacionamento.getLocalizacao());
    }
}
