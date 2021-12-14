package boardgame;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Pecas [][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new BoardException("Erro: o n�mero de linhas e colunas precisa ser maior que 1");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Pecas [linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Pecas pecas(int linha, int coluna) {
		if (!posicaoExistente(linha, coluna)) {
			throw new BoardException ("Erro: posic�o inexistente");
		}
		return pecas [linha][coluna];
	}
	
	public Pecas pecas(LinhasEColunas posicao) {
		if (!posicaoExistente(posicao)) {
			throw new BoardException ("Erro: posic�o inexistente");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void PosicaoPeca (Pecas peca, LinhasEColunas posicao) {
		if (posicaoComPeca(posicao)) {
			throw new BoardException ("Erro: Posi��o j� ocupada");
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Pecas removerPeca(LinhasEColunas posicao) {
		if (!posicaoExistente(posicao)) {
			throw new BoardException ("Erro: posic�o inexistente");
		}
		
		if (pecas(posicao) == null) {
			return null;
		}
		
		Pecas aux = pecas(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
		
	}
	
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(LinhasEColunas posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean posicaoComPeca(LinhasEColunas posicao) {
		if (!posicaoExistente(posicao)) {
			throw new BoardException ("Erro: posic�o inexistente");
		}
		return pecas(posicao) != null;
	}
}
