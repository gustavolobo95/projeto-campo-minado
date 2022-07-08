package br.com.cursocod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

	private int quantidadeDeLinhas;
	private int quantidadeDeColunas;
	private int quantidadeDeMinas;
	
	private final List<Campo> campos = new ArrayList<Campo>();

	public Tabuleiro(int quantidadeDeLinhas, int quantidadeDeColunas, int quantidadeDeMinas) {
		this.quantidadeDeLinhas = quantidadeDeLinhas;
		this.quantidadeDeColunas = quantidadeDeColunas;
		this.quantidadeDeMinas = quantidadeDeMinas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	private void gerarCampos() {
		
	}
	
	private void associarVizinhos() {
		
	}
	
	private void sortearMinas() {

	}
	
}
