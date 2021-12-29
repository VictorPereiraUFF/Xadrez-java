package xadrez;

import boardgame.LinhasEColunas;

public class XadrezPosicao {

	private char coluna;
	private int linha;
	
	public XadrezPosicao(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro: dados inválidos");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected LinhasEColunas toPosicao() {
		return new LinhasEColunas(8 - linha, coluna - 'a');
	}
	
	protected static XadrezPosicao fromLinhasEColunas(LinhasEColunas posicao) {
		return new XadrezPosicao((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
	
	
	
}
