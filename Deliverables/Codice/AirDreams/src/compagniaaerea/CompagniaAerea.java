package compagniaaerea;

public class CompagniaAerea {

	private String nome;
	private String sito;
	
	public CompagniaAerea() {
		super();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSito() {
		return sito;
	}
	
	public void setSito(String sito) {
		this.sito = sito;
	}

	@Override
	public String toString() {
		return "CompagniaAerea [nome=" + nome + ", sito=" + sito + "]";
	}
}
