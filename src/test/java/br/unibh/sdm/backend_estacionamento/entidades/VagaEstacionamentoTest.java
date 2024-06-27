package br.unibh.sdm.backend_estacionamento.entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VagaEstacionamentoTest {
    private VagaEstacionamento vagaEstacionamento;
    private Date dataAtual;

    @BeforeEach
    public void setUp() {
        dataAtual = new Date();
        vagaEstacionamento = new VagaEstacionamento("1", "A1", true, dataAtual);
    }

    @Test
    public void testGetId() {
        assertEquals("1", vagaEstacionamento.getId());
    }

    @Test
    public void testSetId() {
        vagaEstacionamento.setId("2");
        assertEquals("2", vagaEstacionamento.getId());
    }

    @Test
    public void testGetLocalizacao() {
        assertEquals("A1", vagaEstacionamento.getLocalizacao());
    }

    @Test
    public void testSetLocalizacao() {
        vagaEstacionamento.setLocalizacao("B2");
        assertEquals("B2", vagaEstacionamento.getLocalizacao());
    }

    @Test
    public void testIsDisponivel() {
        assertTrue(vagaEstacionamento.isDisponivel());
    }

    @Test
    public void testSetDisponivel() {
        vagaEstacionamento.setDisponivel(false);
        assertFalse(vagaEstacionamento.isDisponivel());
    }

    @Test
    public void testGetDataUltimaAtualizacao() {
        assertEquals(dataAtual, vagaEstacionamento.getDataUltimaAtualizacao());
    }

    @Test
    public void testSetDataUltimaAtualizacao() {
        Date novaData = new Date();
        vagaEstacionamento.setDataUltimaAtualizacao(novaData);
        assertEquals(novaData, vagaEstacionamento.getDataUltimaAtualizacao());
    }

    @Test
    public void testToString() {
        String expected = "VagaEstacionamento [id=1, localizacao=A1, disponivel=true, dataUltimaAtualizacao=" + dataAtual + "]";
        assertEquals(expected, vagaEstacionamento.toString());
    }

    @Test
    public void testHashCode() {
        VagaEstacionamento outraVaga = new VagaEstacionamento("1", "A1", true, dataAtual);
        assertEquals(vagaEstacionamento.hashCode(), outraVaga.hashCode());
    }

    @Test
    public void testEquals() {
        VagaEstacionamento outraVaga = new VagaEstacionamento("1", "A1", true, dataAtual);
        assertTrue(vagaEstacionamento.equals(outraVaga));
        
        VagaEstacionamento diferenteVaga = new VagaEstacionamento("2", "B2", false, new Date());
        assertFalse(vagaEstacionamento.equals(diferenteVaga));
    }
}
