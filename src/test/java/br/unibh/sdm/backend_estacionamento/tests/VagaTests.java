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

import br.unibh.sdm.backend_estacionamento.entidades.Vaga;
import br.unibh.sdm.backend_estacionamento.persistencia.VagaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, VagaTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VagaTests {

    private static Logger LOGGER = LoggerFactory.getLogger(VagaTests.class);

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = VagaRepository.class)
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
    private VagaRepository repository;

    @Test
    public void teste1Criacao() {
        LOGGER.info("Criando objetos...");
        Vaga v1 = new Vaga("VAGA1", "Vaga 1", "Livre");
        Vaga v2 = new Vaga("VAGA2", "Vaga 2", "Ocupada");
        Vaga v3 = new Vaga("VAGA3", "Vaga 3", "Livre");
        repository.save(v1);
        repository.save(v2);
        repository.save(v3);
        Iterable<Vaga> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Vaga vaga : lista) {
            LOGGER.info(vaga.toString());
        }
        LOGGER.info("Pesquisando um objeto");
        List<Vaga> result = repository.findByCodigo("VAGA1");
        assertEquals(result.size(), 1);
        LOGGER.info("Encontrado: {}", result.size());
    }

    @Test
    public void teste2Exclusao() {
        LOGGER.info("Excluindo objetos...");
        List<Vaga> result = repository.findByCodigo("VAGA1");
        for (Vaga vaga : result) {
            LOGGER.info("Excluindo Vaga id = " + vaga.getId());
            repository.delete(vaga);
        }
        result = repository.findByCodigo("VAGA1");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }
}
