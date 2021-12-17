package xadrez.pecas;

import boardgame.Tabuleiro;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;

public class Peão extends XadrezPecas{

	public Peão(Tabuleiro tabuleiro, XadrezJogador xadrezJogador) {
		super(tabuleiro, xadrezJogador);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
