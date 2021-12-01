package xadrez.pecas;

import boardgame.Tabuleiro;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;

public class Rei extends XadrezPecas{

	public Rei(Tabuleiro tabuleiro, XadrezJogador xadrezJogador) {
		super(tabuleiro, xadrezJogador);

	}

	@Override
	public String toString() {
		return "K";
	}
}
