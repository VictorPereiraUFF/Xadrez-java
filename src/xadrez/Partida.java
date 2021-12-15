package xadrez;

import boardgame.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peão;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {
	
	private Tabuleiro tabuleiro;
	
	public Partida() {
		tabuleiro = new Tabuleiro (8, 8);
		setupInicial();
	}

	public XadrezPecas[][] getPecas() {
		XadrezPecas [][] mat = new XadrezPecas[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (XadrezPecas) tabuleiro.pecas(i,j);
					
				}
			}
			return mat;
		}
		
		private void posicaoPecaNova (char coluna, int linha, XadrezPecas pecas) {
			tabuleiro.PosicaoPeca(pecas, new XadrezPosicao(coluna, linha).toPosicao());
		}
	
	
		private void setupInicial() {
			posicaoPecaNova('d', 1, new Rei(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('e', 1, new Rainha(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('a', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('h', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('b', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('g', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('c', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('f', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('d', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('e', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('a', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('h', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('b', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('g', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('c', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('f', 2, new Peão(tabuleiro, XadrezJogador.BRANCO));
			posicaoPecaNova('d', 8, new Rei(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('e', 8, new Rainha(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('a', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('h', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('b', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('g', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('c', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('f', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('d', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('e', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('a', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('h', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('b', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('g', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('c', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
			posicaoPecaNova('f', 7, new Peão(tabuleiro, XadrezJogador.PRETO));
		}


		

}
