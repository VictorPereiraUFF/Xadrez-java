package xadrez;

import boardgame.Pecas;
import boardgame.Tabuleiro;

public abstract class XadrezPecas extends Pecas{
	
	private XadrezJogador xadrezJogador;

	public XadrezPecas(Tabuleiro tabuleiro, XadrezJogador xadrezJogador) {
		super(tabuleiro);
		this.xadrezJogador = xadrezJogador;
	}

	public XadrezJogador getXadrezJogador() {
		return xadrezJogador;
	}

}
