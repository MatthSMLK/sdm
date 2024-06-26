package br.unibh.sdm.backend_estacionamento.entidades;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "vaga_estacionamento")
public class VagaEstacionamento {

	private String id;
	private String localizacao;
	private boolean disponivel;
	private Date dataUltimaAtualizacao;
	
	public VagaEstacionamento() {
		super();
	}

	public VagaEstacionamento(String id, String localizacao, boolean disponivel, Date dataUltimaAtualizacao) {
		super();
		this.id = id;
		this.localizacao = localizacao;
		this.disponivel = disponivel;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	@DynamoDBHashKey
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDBAttribute
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@DynamoDBAttribute
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@DynamoDBAttribute
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	@Override
	public String toString() {
		return "VagaEstacionamento [id=" + id + ", localizacao=" + localizacao + ", disponivel=" + disponivel + ", dataUltimaAtualizacao="
				+ dataUltimaAtualizacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((dataUltimaAtualizacao == null) ? 0 : dataUltimaAtualizacao.hashCode());
		result = prime * result + ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + (disponivel ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VagaEstacionamento other = (VagaEstacionamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (dataUltimaAtualizacao == null) {
			if (other.dataUltimaAtualizacao != null)
				return false;
		} else if (!dataUltimaAtualizacao.equals(other.dataUltimaAtualizacao))
			return false;
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (disponivel != other.disponivel)
			return false;
		return true;
	}
}
