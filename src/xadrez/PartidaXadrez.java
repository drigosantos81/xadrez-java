package xadrez;

import boardgame.Peca;
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
	
	public PecaXadrez performXadrezMove(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao) {
		Posicao origem = origemPosicao.toPosicao();
		Posicao destino = destinoPosicao.toPosicao();
		validarOrigemPosicao(origem);
		Peca pecaCapturada = makeMove(origem, destino);
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca makeMove(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.posicaoPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peca nessa posicao");
		}
		if (!tabuleiro.peca(posicao).temMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void lugarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
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
