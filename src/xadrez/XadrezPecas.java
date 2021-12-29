package xadrez;

import boardgame.LinhasEColunas;
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
	
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.fromLinhasEColunas(posicao);
	}
	
	protected boolean pecaAdversaria (LinhasEColunas posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().pecas(posicao);
		return p != null && p.getXadrezJogador() != xadrezJogador;
	}

}
