package xadrez;

import java.util.ArrayList;
import java.util.List;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecasNaMesa = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	private int turn;
	private Cor jogadorAtual;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turn = 1;
		jogadorAtual = Cor.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	
	public boolean[][] possibelMoves(XadrezPosicao origemPosicao) {
		Posicao posicao = origemPosicao.toPosicao();
		validarOrigemPosicao(posicao);
		return tabuleiro.peca(posicao).possibleMoves();
	}
	
	public PecaXadrez performXadrezMove(XadrezPosicao origemPosicao, XadrezPosicao destinoPosicao) {
		Posicao origem = origemPosicao.toPosicao();
		Posicao destino = destinoPosicao.toPosicao();
		validarOrigemPosicao(origem);
		validarDestinoPosicao(origem, destino);
		Peca pecaCapturada = makeMove(origem, destino);
		nextTurn();
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca makeMove(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.posicaoPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNaMesa.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if (!tabuleiro.temUmaPeca(posicao)) {
			throw new XadrezException("Nao existe peca nessa posicao");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peca escolhida nao eh sua");
		}
		if (!tabuleiro.peca(posicao).temMovimentoPossivel()) {
			throw new XadrezException("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validarDestinoPosicao(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possibleMoves(destino)) {
			throw new XadrezException("A peca escolhida nao pode se mover para a posicao de destino");
		}
	}
	
	private void nextTurn() {
		turn++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private void lugarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.posicaoPeca(peca, new XadrezPosicao(coluna, linha).toPosicao());
		pecasNaMesa.add(peca);
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
