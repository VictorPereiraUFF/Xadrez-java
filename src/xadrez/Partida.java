package xadrez;

import boardgame.LinhasEColunas;
import boardgame.Tabuleiro;
import xadrez.pecas.Peão;
import xadrez.pecas.Rei;
import xadrez.pecas.Rainha;
import xadrez.pecas.Torre;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Bispo;

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
			tabuleiro.PosicaoPeca(new Peão(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(2, 6));
			tabuleiro.PosicaoPeca(new Rei(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 3));
			tabuleiro.PosicaoPeca(new Rainha(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 4));
			tabuleiro.PosicaoPeca(new Torre(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 0));
			tabuleiro.PosicaoPeca(new Torre(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 7));
			tabuleiro.PosicaoPeca(new Bispo(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 2));
			tabuleiro.PosicaoPeca(new Bispo(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 5));
			tabuleiro.PosicaoPeca(new Cavalo(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 1));
			tabuleiro.PosicaoPeca(new Cavalo(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(7, 6));



			
		}


		

}
