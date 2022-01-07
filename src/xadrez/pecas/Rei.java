package xadrez.pecas;

import boardgame.LinhasEColunas;
import boardgame.Tabuleiro;
import xadrez.Partida;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;

public class Rei extends XadrezPecas{
	
	private Partida partida;

	public Rei(Tabuleiro tabuleiro, XadrezJogador xadrezJogador, Partida partida) {
		super(tabuleiro, xadrezJogador);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "K";
	}
	
	private boolean podeMover(LinhasEColunas posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().pecas(posicao);
		return p == null || p.getXadrezJogador() != getXadrezJogador();
	}
	
	private boolean testarRoque(LinhasEColunas posicao) {
		XadrezPecas p = (XadrezPecas)getTabuleiro().pecas(posicao);
		return p != null && p instanceof Torre && p.getXadrezJogador() == getXadrezJogador() && p.getContagem() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		LinhasEColunas p = new LinhasEColunas (0, 0);
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		/*if (getContagem() == 0 && !partida.getXeque()) {
			LinhasEColunas PT1 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() + 3);
			if (testarRoque(PT1)) {
				LinhasEColunas p1 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() + 1);
				LinhasEColunas p2 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() +  2] = true;
				}

			}
			
			LinhasEColunas PT2 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() - 4);
			if (testarRoque(PT2)) {
				LinhasEColunas p1 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() - 1);
				LinhasEColunas p2 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() - 2);
				LinhasEColunas p3 = new LinhasEColunas(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null && getTabuleiro().pecas(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() -  2] = true;
				}

			}
		}*/
		
		return mat;
	}
}
