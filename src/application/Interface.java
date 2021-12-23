package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Partida;
import xadrez.XadrezJogador;
import xadrez.XadrezPecas;
import xadrez.XadrezPosicao;

public class Interface {
	
	public static void limparTela() {
		System.out.flush();
		}
	
	public static XadrezPosicao lerXadrezPosicao(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new XadrezPosicao(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException ("Erro: Dados Inválidos");
		}
	}
	
	public static void printPartida(Partida partida, List<XadrezPecas> capturadas) {
		printTabuleiro(partida.getPecas());
		System.out.println();
		printPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno atual: " + partida.getTurno());
		System.out.println("Aguardando jogador " + partida.getJogadorAtual() + " fazer sua jogada");
	}
	
	public static void printTabuleiro(XadrezPecas[][] pecas) {
		for (int i=0; i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pecas.length; j++) {
				printPecas(pecas[i][j], false);				
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printTabuleiro(XadrezPecas[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i=0; i<pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pecas.length; j++) {
				printPecas(pecas[i][j], movimentosPossiveis[i][j]);				
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPecas(XadrezPecas pecas, boolean fundo) {
		if (pecas == null) {
			System.out.print("-");	
		}
		else {
			System.out.print(pecas);
		}
		System.out.print(" ");
		
	}
	
	private static void printPecasCapturadas(List<XadrezPecas> capturadas) {
		List<XadrezPecas> branco = capturadas.stream().filter(x -> x.getXadrezJogador() == XadrezJogador.BRANCO).collect(Collectors.toList());
		List<XadrezPecas> preto = capturadas.stream().filter(x -> x.getXadrezJogador() == XadrezJogador.PRETO).collect(Collectors.toList());
		System.out.println("Peças capturadas: ");
		System.out.print("Brancas: ");
		System.out.println(Arrays.toString(branco.toArray()));
		System.out.print("Pretas: ");
		System.out.println(Arrays.toString(preto.toArray()));
		
	}
}
