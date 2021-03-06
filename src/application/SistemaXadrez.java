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
		List<XadrezPecas> capturadas = new ArrayList<>();
		
		while (!partida.getXequemate()) {
			try {
				Interface.limparTela();
				Interface.printPartida(partida, capturadas);
				System.out.println();
				System.out.print("Posi??o de origem: ");
				XadrezPosicao origem = Interface.lerXadrezPosicao(sc); 
				
				boolean[][] movimentosPossiveis = partida.movimentosPossiveis(origem);
				Interface.limparTela();
				Interface.printTabuleiro(partida.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Posi??o de destino: ");
				XadrezPosicao destino = Interface.lerXadrezPosicao(sc); 
				
				XadrezPecas pecaCapturada = partida.moverPeca(origem, destino);
				
				if (pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
				
				if (partida.getPromocao() != null) {
					System.out.print("Escolha a pe?a para a promo??o do seu pe?o: ");
					String tipo = sc.nextLine();
					partida.pecaPromovida(tipo);
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
		
		Interface.limparTela();
		Interface.printPartida(partida, capturadas);
		
	}
}
