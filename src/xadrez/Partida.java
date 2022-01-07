package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.LinhasEColunas;
import boardgame.Pecas;
import boardgame.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peão;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	private int turno;
	private XadrezJogador jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequemate;
	private XadrezPecas enPassant;
	
	private List<Pecas> pecasNoTabuleiro = new ArrayList<>();
	private List<Pecas> pecasCapturadas = new ArrayList<>();

	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = XadrezJogador.BRANCO;
		setupInicial();
	}

	public int getTurno() {
		return turno;
	}

	public XadrezJogador getJogadorAtual() {
		return jogadorAtual;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequemate() {
		return xequemate;
	}

	public XadrezPecas getEnPassant() {
		return enPassant;
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
		
		if (testarXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Erro: você está se colocando em xeque");
		}
		
		XadrezPecas pecaMexida = (XadrezPecas)tabuleiro.pecas(destino);
		
		xeque = (testarXeque(adversario(jogadorAtual))) ? true : false;
		
		if (testarXequemate(adversario(jogadorAtual))) {
			xequemate = true;
		}
		else {
			proximaRodada();
		}
	
		if (pecaMexida instanceof Peão && (destino.getLinha() == origem.getLinha() - 2) || (destino.getLinha() == origem.getLinha() + 2)) {
			enPassant = pecaMexida;
		}
		
		else {
			enPassant = null;
		}
		
		return (XadrezPecas) pecaCapturada;
	}
	
	private Pecas movimento (LinhasEColunas origem, LinhasEColunas destino) {
		XadrezPecas p = (XadrezPecas)tabuleiro.removerPeca(origem);
		p.contagemCrescente();
		Pecas pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.PosicaoPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		if (p instanceof Rei && destino.getColuna() == origem.getLinha() + 2) {
			LinhasEColunas origemT = new LinhasEColunas(origem.getLinha(), origem.getColuna() + 3);
			LinhasEColunas destinoT = new LinhasEColunas(origem.getLinha(), origem.getColuna() + 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(origemT);
			tabuleiro.PosicaoPeca(torre, destinoT);
			torre.contagemCrescente();
		}
		
		if (p instanceof Rei && destino.getColuna() == origem.getLinha() - 2) {
			LinhasEColunas origemT = new LinhasEColunas(origem.getLinha(), origem.getColuna() - 4);
			LinhasEColunas destinoT = new LinhasEColunas(origem.getLinha(), origem.getColuna() - 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(origemT);
			tabuleiro.PosicaoPeca(torre, destinoT);
			torre.contagemCrescente();
		}
		
		if (p instanceof Peão) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				LinhasEColunas posicaoPeao;
				if (p.getXadrezJogador() == XadrezJogador.BRANCO) {
					posicaoPeao = new LinhasEColunas(destino.getLinha() + 1, destino.getColuna());
				}
				else {
					posicaoPeao = new LinhasEColunas(destino.getLinha() - 1, destino.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;	
	}
	
	private void desfazerMovimento(LinhasEColunas origem, LinhasEColunas destino, Pecas pecaCapturada) {
		XadrezPecas p = (XadrezPecas)tabuleiro.removerPeca(destino);
		p.contagemDecrescente();
		tabuleiro.PosicaoPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.PosicaoPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
		/*if (p instanceof Rei && destino.getColuna() == origem.getLinha() + 2) {
			LinhasEColunas origemT = new LinhasEColunas(origem.getLinha(), origem.getColuna() + 3);
			LinhasEColunas destinoT = new LinhasEColunas(origem.getLinha(), origem.getColuna() + 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(destinoT);
			tabuleiro.PosicaoPeca(torre, origemT);
			torre.contagemDecrescente();
		}
		
		if (p instanceof Rei && destino.getColuna() == origem.getLinha() - 2) {
			LinhasEColunas origemT = new LinhasEColunas(origem.getLinha(), origem.getColuna() - 4);
			LinhasEColunas destinoT = new LinhasEColunas(origem.getLinha(), origem.getColuna() - 1);
			XadrezPecas torre = (XadrezPecas)tabuleiro.removerPeca(destinoT);
			tabuleiro.PosicaoPeca(torre, origemT);
			torre.contagemDecrescente();
		}*/
		
		if (p instanceof Peão) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassant) {
				XadrezPecas peao = (XadrezPecas)tabuleiro.removerPeca(destino);
				LinhasEColunas posicaoPeao;
				if (p.getXadrezJogador() == XadrezJogador.BRANCO) {
					posicaoPeao = new LinhasEColunas(3, destino.getColuna());
				}
				else {
					posicaoPeao = new LinhasEColunas(4, destino.getColuna());
				}
				
				tabuleiro.PosicaoPeca(peao, posicaoPeao);
				
			}
		}
	}
	
	private void validarPosicaoOrigem(LinhasEColunas posicao) {
		if (!tabuleiro.posicaoComPeca(posicao)) {
			throw new XadrezException("Erro: a posição de origem declarada está vazia");
		}
		if(jogadorAtual != ((XadrezPecas)tabuleiro.pecas(posicao)).getXadrezJogador()) {
			throw new XadrezException("A peça escolhida é do jogador adversário, escolha uma peça sua");
		}
		if (!tabuleiro.pecas(posicao).issoEPossivel()) {
			throw new XadrezException("Erro: esse movimento não é permitido");
		}
	}
	
	private void validarPosicaoDestino (LinhasEColunas origem, LinhasEColunas destino) {
		if (!tabuleiro.pecas(origem).movimentoPossivel(destino)) {
			throw new XadrezException("Erro: a peça escolhida não pode alcançar a casa informada");
		}
	}
	
	private void proximaRodada() {
		turno++;
		jogadorAtual = (jogadorAtual == XadrezJogador.BRANCO) ? XadrezJogador.PRETO : XadrezJogador.BRANCO;
	}
	
	private XadrezJogador adversario(XadrezJogador xadrezJogador) {
		return (xadrezJogador == XadrezJogador.BRANCO ? XadrezJogador.PRETO : XadrezJogador.BRANCO);
	}
	
	private XadrezPecas rei (XadrezJogador xadrezJogador) {
		List<Pecas> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getXadrezJogador() ==  xadrezJogador).collect(Collectors.toList());
		for (Pecas p : list) {
			if (p instanceof Rei) {
				return (XadrezPecas)p;
			}
		}
		throw new IllegalStateException ("Erro: não há um rei " + xadrezJogador + " no tabuleiro");
	}
	
	private boolean testarXeque(XadrezJogador xadrezJogador) {
		LinhasEColunas posicaoDoRei = rei(xadrezJogador).getXadrezPosicao().toPosicao();
		List<Pecas> pecasAdversarias = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getXadrezJogador() ==  adversario(xadrezJogador)).collect(Collectors.toList());
		for (Pecas p : pecasAdversarias) {
			boolean [][] mat = p.movimentosPossiveis();
			if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testarXequemate(XadrezJogador xadrezJogador) {
		if(!testarXeque(xadrezJogador)) {
			return false;
		}
		List<Pecas> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPecas)x).getXadrezJogador() ==  adversario(xadrezJogador)).collect(Collectors.toList());
		for (Pecas p : list ) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						LinhasEColunas origem = ((XadrezPecas)p).getXadrezPosicao().toPosicao();
						LinhasEColunas destino = new LinhasEColunas(i, j);
						Pecas pecaCapturada = movimento(origem, destino);
						boolean testarXeque = testarXeque(xadrezJogador);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testarXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void posicaoPecaNova(char coluna, int linha, XadrezPecas pecas) {
		tabuleiro.PosicaoPeca(pecas, new XadrezPosicao(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(pecas);
	}

	private void setupInicial() {
		posicaoPecaNova('a', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('b', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('c', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('d', 1, new Rainha(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('e', 1, new Rei(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('f', 1, new Bispo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('g', 1, new Cavalo(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('h', 1, new Torre(tabuleiro, XadrezJogador.BRANCO));
		posicaoPecaNova('a', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('b', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('c', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('d', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('e', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('f', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('g', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('h', 2, new Peão(tabuleiro, XadrezJogador.BRANCO, this));
		posicaoPecaNova('a', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('b', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('c', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('d', 8, new Rei(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('e', 8, new Rainha(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('f', 8, new Bispo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('g', 8, new Cavalo(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('h', 8, new Torre(tabuleiro, XadrezJogador.PRETO));
		posicaoPecaNova('a', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('b', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('c', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('d', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('e', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('f', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('g', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));
		posicaoPecaNova('h', 7, new Peão(tabuleiro, XadrezJogador.PRETO, this));


	}

}
