package application;

import java.util.Scanner;

import xadrez.Partida;

public class SistemaXadrez {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bora jogar xadrez?");
		System.out.println();

		Partida partida = new Partida();
		Interface.printTabuleiro(partida.getPecas());
		
		
		
		
		
		
		sc.close();
		
		
		}
}
