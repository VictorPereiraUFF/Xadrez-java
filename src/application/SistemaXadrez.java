package application;

import java.util.Scanner;

import xadrez.Partida;
import xadrez.XadrezPecas;
import xadrez.XadrezPosicao;

public class SistemaXadrez {

	public static void main(String[] args) {
				
		System.out.println("Bora jogar xadrez?");
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		
		while (true) {
			Interface.printTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Posição de origem: ");
			XadrezPosicao origem = Interface.lerXadrezPosicao(sc); 
			System.out.println();
			System.out.print("Posição de destino: ");
			XadrezPosicao destino = Interface.lerXadrezPosicao(sc); 
			
			XadrezPecas pecaCapturada = partida.moverPeca(origem, destino);
		}
		
		
		
		
		
		
		
		
		
		}
}
