package boardgame;

public abstract class Pecas {

	protected LinhasEColunas posicao;
	private Tabuleiro tabuleiro;
	
	public Pecas(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean [][] movimentosPossiveis();
	
	public boolean movimentoPossivel(LinhasEColunas posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean issoEPossivel() {
		boolean [][] mat = movimentosPossiveis();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; i < mat.length; j++) {

				
					return true;
				}	
			}
		
		return false;
	}
	
}
