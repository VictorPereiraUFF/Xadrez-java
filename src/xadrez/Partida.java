package xadrez;

import boardgame.LinhasEColunas;
import boardgame.Tabuleiro;
import xadrez.pecas.Peão;

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
		
		private void setupInicial() {
			tabuleiro.PosicaoPeca(new Peão(tabuleiro, XadrezJogador.BRANCO), new LinhasEColunas(2, 6));			
		}


		

}
