package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezException;
import xadrez.XadrezPosicao;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList<>();

		while (!partidaXadrez.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez, capturada);
				System.out.println();
				System.out.print("Origem: ");
				XadrezPosicao origem = UI.readXadrezPosicao(sc);
				
				boolean[][] possibleMoves = partidaXadrez.possibelMoves(origem);
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.getPecas(), possibleMoves);	
				System.out.println();
				System.out.print("Destino: ");
				XadrezPosicao destino = UI.readXadrezPosicao(sc);
	
				PecaXadrez pecaCapturada = partidaXadrez.performXadrezMove(origem, destino);
				
				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printPartida(partidaXadrez, capturada);
		System.out.println("FIM DE JOGO.");
	}
}
