package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Partida;
import xadrez.XadrezException;
import xadrez.XadrezPecas;
import xadrez.XadrezPosicao;

public class SistemaXadrez {

	public static void main(String[] args) {
				
		System.out.println("Bora jogar xadrez?");
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		
		while (true) {
			try {
				Interface.limparTela();
				Interface.printPartida(partida);
				System.out.println();
				System.out.print("Posição de origem: ");
				XadrezPosicao origem = Interface.lerXadrezPosicao(sc); 
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				Interface.limparTela();
				Interface.printTabuleiro(partida.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Posição de destino: ");
				XadrezPosicao destino = Interface.lerXadrezPosicao(sc); 
				
				XadrezPecas pecaCapturada = partida.moverPeca(origem, destino);
			}
			catch (XadrezException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		
		
		
		
		
		
		
		
		}
}
