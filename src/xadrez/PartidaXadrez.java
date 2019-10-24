package xadrez;

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
	
	private void lugarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoPeca(peca, new XadrexzPosicao(coluna, linha).toPosicao());
	}
	
	private void initialSetup() {
		lugarNovaPeca('c', 1, new Rook(tabuleiro, Cor.WHITE));
		lugarNovaPeca('c', 2, new Rook(tabuleiro, Cor.WHITE));
		lugarNovaPeca('d', 2, new Rook(tabuleiro, Cor.WHITE));
		lugarNovaPeca('e', 2, new Rook(tabuleiro, Cor.WHITE));
		lugarNovaPeca('e', 1, new Rook(tabuleiro, Cor.WHITE));
		lugarNovaPeca('d', 1, new King(tabuleiro, Cor.WHITE));

		lugarNovaPeca('c', 7, new Rook(tabuleiro, Cor.BLACK));
		lugarNovaPeca('c', 8, new Rook(tabuleiro, Cor.BLACK));
		lugarNovaPeca('d', 7, new Rook(tabuleiro, Cor.BLACK));
		lugarNovaPeca('e', 7, new Rook(tabuleiro, Cor.BLACK));
		lugarNovaPeca('e', 8, new Rook(tabuleiro, Cor.BLACK));
		lugarNovaPeca('d', 8, new King(tabuleiro, Cor.BLACK));
		
		//tabuleiro.posicaoPeca(new King(tabuleiro, Cor.BLACK), new Posicao(0, 4));
		//tabuleiro.posicaoPeca(new King(tabuleiro, Cor.WHITE), new Posicao(7, 4));
	}

}
