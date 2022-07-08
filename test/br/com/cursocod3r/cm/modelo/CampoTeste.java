package br.com.cursocod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cursocod3r.cm.excecao.ExplosaoException;

class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoEmCima() {
		Campo vizinho = new Campo (2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoEmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testeVizinhoDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoFalse() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeValorPadraoAtributoMinado() {
		assertFalse(campo.isMinado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasVezes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void abrirCampoNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void abrirCampoNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirCampoMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirCamposVizinhos() {
		Campo vizinhoDiagonalSuperiorEsquerda = new Campo(2, 2);
		Campo vizinhoSuperior = new Campo(2, 3);
		Campo vizinhoDiagonalSuperiorDireita = new Campo(2, 4);
		Campo vizinhoEsquerda = new Campo(3, 2);
		Campo vizinhoDireita = new Campo(3, 4);
		Campo vizinhoDiagonalInferiorEsquerda = new Campo(4, 2);
		Campo vizinhoInferior = new Campo(4, 3);
		Campo vizinhoDiagonalInferiorDireita = new Campo(4, 4);
		
		campo.adicionarVizinho(vizinhoDiagonalSuperiorEsquerda);
		campo.adicionarVizinho(vizinhoSuperior);
		campo.adicionarVizinho(vizinhoDiagonalSuperiorDireita);
		campo.adicionarVizinho(vizinhoEsquerda);
		campo.adicionarVizinho(vizinhoDireita);
		campo.adicionarVizinho(vizinhoDiagonalInferiorEsquerda);
		campo.adicionarVizinho(vizinhoInferior);
		campo.adicionarVizinho(vizinhoDiagonalInferiorDireita);
		
		campo.abrir();
		
		assertTrue(vizinhoDiagonalSuperiorEsquerda.isAberto() &&
					vizinhoSuperior.isAberto() &&
					vizinhoDiagonalSuperiorDireita.isAberto() &&
					vizinhoEsquerda.isAberto() &&
					vizinhoDireita.isAberto() &&
					vizinhoDiagonalInferiorEsquerda.isAberto() &&
					vizinhoInferior.isAberto() &&
					vizinhoDiagonalInferiorDireita.isAberto());
 	}
	
	@Test
	void testeAbrirCamposVizinhosRecursivo() {
		Campo vizinho = new Campo(2, 2);
		Campo vizinhoDoVizinho = new Campo(1, 1);
		
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		campo.adicionarVizinho(vizinho);
		
		campo.abrir();
		
		assertTrue(vizinho.isAberto() && vizinhoDoVizinho.isAberto());
	}
	
	@Test
	void testeAbrirCamposVizinhosNaoSeguros() {
		Campo vizinho = new Campo(2, 2);
		Campo vizinhoDoVizinho = new Campo(1, 1);
		
		vizinho.adicionarVizinho(vizinhoDoVizinho);
		campo.adicionarVizinho(vizinho);
		
		vizinhoDoVizinho.minar();
		campo.abrir();
		
		assertFalse(vizinho.isAberto() && vizinhoDoVizinho.isAberto());
	}
	
}
