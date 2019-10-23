package xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		initialSetup();
	}
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		tabuleiro.posicaoPeca(new Rook(tabuleiro, Cor.WHITE), new Posicao(2, 1));
		tabuleiro.posicaoPeca(new King(tabuleiro, Cor.BLACK), new Posicao(0, 4));
		tabuleiro.posicaoPeca(new King(tabuleiro, Cor.WHITE), new Posicao(7, 4));
	}

}
