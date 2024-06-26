package br.unibh.sdm.backend_estacionamento.entidades;

public enum EstadoVaga {

	DISPONIVEL("Disponível"), 
	OCUPADA("Ocupada");

	private String descricao;

	EstadoVaga(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
