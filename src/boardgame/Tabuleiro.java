package boardgame;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Pecas [][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Pecas [linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public Pecas pecas(int linha, int coluna) {
		return pecas [linha][coluna];
	}
	
	public Pecas pecas(LinhasEColunas posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void PosicaoPeca (Pecas peca, LinhasEColunas posicao) {
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	
}
