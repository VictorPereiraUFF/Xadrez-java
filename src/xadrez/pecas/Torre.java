package xadrez.pecas;

import boardgame.Tabuleiro;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;

public class Torre extends XadrezPecas {

	public Torre(Tabuleiro tabuleiro, XadrezJogador xadrezJogador) {
		super(tabuleiro, xadrezJogador);
	}
	
	@Override
	public String toString() {
		return "T";
	}

	
}