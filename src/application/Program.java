package application;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosicao;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while (true) {
		UI.printTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		System.out.print("Origem: ");
		XadrezPosicao origem = UI.readXadrezPosicao(sc);
		
		System.out.println();
		System.out.println("Destino: ");
		XadrezPosicao destino = UI.readXadrezPosicao(sc);
		
		PecaXadrez pecaCapturada = partidaXadrez.performXadrezMove(origem, destino);
		}
	}
}
