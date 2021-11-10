package boardgame;

public class Pecas {

	protected LinhasEColunas posicao;
	private Tabuleiro tabuleiro;
	
	public Pecas(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
