package br.unibh.sdm.backend_estacionamento.entidades;

public enum ReservaEstacionamento {

	DISPONIVEL("Disponível"), 
	OCUPADA("Ocupada");

	private String descricao;

	ReservaEstacionamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
