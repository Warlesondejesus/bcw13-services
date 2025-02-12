package SoulCode.Servicos.Models;

public enum StatusServico {
	
	RECEBIDO("Recebido"),
	ATRIBUIDO("Atribuido"),
	CONCLUIDO("Concluido"),
	ARQUIVADO("Arquivado");
	
	private String descricao;
	
	StatusServico(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
