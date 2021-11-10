package application;

import java.util.Scanner;

public class SistemaXadrez {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bora jogar xadrez?");
		int l = sc.nextInt();
		int c = sc.nextInt();

		System.out.println("Posição: " + l +", " + c);
	}
}
