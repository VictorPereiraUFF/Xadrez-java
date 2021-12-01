package xadrez;

import boardgame.Tabuleiro;

public class Partida {
	
	private Tabuleiro tabuleiro;
	
	public Partida() {
		tabuleiro = new Tabuleiro (8, 8);
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
	

		

}
