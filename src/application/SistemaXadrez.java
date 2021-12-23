package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		List<Partida> capturadas = new ArrayList<>();
		
		while (true) {
			try {
				Interface.limparTela();
				Interface.printPartida(partida, capturadas);
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
				
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
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
