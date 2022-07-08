package br.com.cursocod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabuleiroTeste {

	private Tabuleiro tabuleiro;
	
	@BeforeEach
	void iniciarTabuleiro() {
		tabuleiro = new Tabuleiro(6, 6, 6);	
	}
	
	@Test
	void testeGeracaoDeCampos() {
		long contador = tabuleiro.contarCampos();
		assertEquals(36, contador);
	}
	
	@Test
	void testeAlternarMarcacao() {
		tabuleiro.alternarMarcacao(3, 3);
		assertEquals("X", tabuleiro.getCampo(3, 3).toString());
	}
	
	@Test
	void testeAbrirCampo() {
		tabuleiro.reiniciarSemSortearMinas();
		tabuleiro.abrirCampo(3, 3);
		assertEquals(" ", tabuleiro.getCampo(3, 3).toString());
	}
	
	@Test
	void testeObjetivoAlcancado() {
		int linha = 0;
		int coluna = 0;
		do {
			for(linha = 0; linha < 6; linha++) {
				for(coluna = 0; coluna < 6; coluna ++) {
					if(tabuleiro.getCampo(linha, coluna).isMinado()) {
						tabuleiro.alternarMarcacao(linha, coluna);
					} else {
						tabuleiro.abrirCampo(linha, coluna);
					}
				}
			}
		} while(!tabuleiro.objetivoAlcancado());
		assertTrue(tabuleiro.objetivoAlcancado());
	}
	
	@Test
	void testeReiniciar() {
		tabuleiro.alternarMarcacao(3, 3);
		tabuleiro.reiniciar();
		assertFalse(tabuleiro.getCampo(3, 3).isMarcado());
	}
	
}
