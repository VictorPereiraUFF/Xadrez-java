package xadrez.pecas;

import boardgame.LinhasEColunas;
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
		LinhasEColunas p = new LinhasEColunas (0,0);
		
		if (getXadrezJogador() == XadrezJogador.BRANCO) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().posicaoComPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			LinhasEColunas p2 = new LinhasEColunas(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().posicaoComPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().posicaoComPeca(p2) && getContagem() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExistente(p) && pecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() +1);
			if (getTabuleiro().posicaoExistente(p) && pecaAdversaria(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			else {
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().posicaoComPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posicao.getLinha() + 2, posicao.getColuna());
				LinhasEColunas p3 = new LinhasEColunas(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().posicaoComPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().posicaoComPeca(p2) && getContagem() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
				if (getTabuleiro().posicaoExistente(p) && pecaAdversaria(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() +1);
				if (getTabuleiro().posicaoExistente(p) && pecaAdversaria(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
			}
		}
		return mat;

	}
}
