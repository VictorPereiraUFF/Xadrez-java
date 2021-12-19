package application;

import java.util.InputMismatchException;
import java.util.Scanner;

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
		if (fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (pecas == null) {
			System.out.print("-");	
		}
		else {
			if (pecas.getXadrezJogador() == XadrezJogador.BRANCO) {
				System.out.print(ANSI_WHITE + pecas + ANSI_RESET);
			}
			else {
				System.out.print(ANSI_YELLOW + pecas + ANSI_RESET);
			}
			System.out.print(pecas);
		}
		System.out.print(" ");
		
		
	}
}
