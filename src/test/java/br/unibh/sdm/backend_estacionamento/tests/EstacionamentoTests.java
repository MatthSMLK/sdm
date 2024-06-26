package br.unibh.sdm.backend_estacionamento.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import br.unibh.sdm.backend_estacionamento.entidades.Estacionamento;
import br.unibh.sdm.backend_estacionamento.persistencia.EstacionamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, EstacionamentoTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstacionamentoTests {

    private static Logger LOGGER = LoggerFactory.getLogger(EstacionamentoTests.class);
    
    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = EstacionamentoRepository.class)
    public static class DynamoDBConfig {

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        public AWSCredentialsProvider amazonAWSCredentialsProvider() {
            return new AWSStaticCredentialsProvider(amazonAWSCredentials());
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
        }
    }

    @Autowired
    private EstacionamentoRepository repository;

    @Test
    public void teste1Criacao() {
        LOGGER.info("Criando objetos...");
        Estacionamento e1 = new Estacionamento("Estacionamento A", "Endereço A", 100);
        Estacionamento e2 = new Estacionamento("Estacionamento B", "Endereço B", 200);
        Estacionamento e3 = new Estacionamento("Estacionamento C", "Endereço C", 150);
        repository.save(e1);
        repository.save(e2);
        repository.save(e3);
        Iterable<Estacionamento> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Estacionamento estacionamento : lista) {
            LOGGER.info(estacionamento.toString());
        }
        LOGGER.info("Pesquisando um objeto");
        List<Estacionamento> result = repository.findByNome("Estacionamento A");
        assertEquals(result.size(), 1);
        LOGGER.info("Encontrado: {}", result.size());
    }

    @Test
    public void teste2Exclusao() {
        LOGGER.info("Excluindo objetos...");
        List<Estacionamento> result = repository.findByNome("Estacionamento A");
        for (Estacionamento estacionamento : result) {
            LOGGER.info("Excluindo Estacionamento id = " + estacionamento.getId());
            repository.delete(estacionamento);
        }
        result = repository.findByNome("Estacionamento A");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }
}
