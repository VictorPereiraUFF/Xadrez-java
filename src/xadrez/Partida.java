package xadrez;

import boardgame.LinhasEColunas;
import boardgame.Pecas;
import boardgame.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Pe�o;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	private Tabuleiro tabuleiro;

	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}

	public XadrezPecas[][] getPecas() {
		XadrezPecas[][] mat = new XadrezPecas[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (XadrezPecas) tabuleiro.pecas(i, j);

			}
		}
		return mat;
	}

	public boolean [][] movimentosPossiveis (XadrezPosicao posicaoOrigem) {
		LinhasEColunas posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.pecas(posicao).movimentosPossiveis();
	}
	
	public XadrezPecas moverPeca(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
		LinhasEColunas origem = posicaoOrigem.toPosicao();
		LinhasEColunas destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Pecas pecaCapturada = movimento(origem, destino);
		return (XadrezPecas) pecaCapturada;
	}
	
	private Pecas movimento (LinhasEColunas origem, LinhasEColunas destino) {
		Pecas p = tabuleiro.removerPeca(origem);
		Pecas pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.PosicaoPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(LinhasEColunas posicao) {
		if (!tabuleiro.posicaoComPeca(posicao)) {
			throw new XadrezException("Erro: a posi��o de origem declarada est� vazia");
		}
		if (!tabuleiro.pecas(posicao).issoEPossivel()) {
			throw new XadrezException("Erro: esse movimento n�o � permitido");
		}
	}
	
	private void validarPosicaoDestino (LinhasEColunas origem, LinhasEColunas destino) {
		if (!tabuleiro.pecas(origem).movimentoPossivel(destino)) {
			throw new XadrezException("Erro: a pe�a escolhida n�o pode alcan�ar a casa informada");
		}
	}

	private void posicaoPecaNova(char coluna, int linha, XadrezPecas pecas) {
		tabuleiro.PosicaoPeca(pecas, new XadrezPosicao(coluna, linha).toPosicao());
	}

	private void setupInicial() {
		posicaoPecaNova('d', 1, new Rei(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('e', 1, new Rainha(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('a', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('h', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('b', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('g', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('c', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('f', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('d', 8, new Rei(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('e', 8, new Rainha(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('a', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('h', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('b', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('g', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('c', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('f', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
	}

}
