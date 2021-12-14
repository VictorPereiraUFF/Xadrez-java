package xadrez.pecas;

import boardgame.Tabuleiro;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;

public class Rainha extends XadrezPecas{

	public Rainha(Tabuleiro tabuleiro, XadrezJogador xadrezJogador) {
		super(tabuleiro, xadrezJogador);
	}

	@Override
	public String toString() {
		return "Q";
	}
}
