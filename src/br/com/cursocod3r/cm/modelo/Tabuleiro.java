package br.com.cursocod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		for(int linhaAtual = 0; linhaAtual < quantidadeDeLinhas; linhaAtual++) {
			for(int colunaAtual = 0; colunaAtual < quantidadeDeColunas; colunaAtual ++) {
				campos.add(new Campo(linhaAtual, colunaAtual));
			}
		}
	}
	
	private void associarVizinhos() {
		for(Campo campo: campos) {
			for(Campo campo2: campos) {
				campo.adicionarVizinho(campo2);
			}
		}
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Random aleatorio = new Random();
		do {
			minasArmadas = campos.stream()
					.filter(campo -> campo.isMinado())
					.count();
			campos.get(aleatorio.nextInt(0, campos.size())).minar();
		} while(minasArmadas < quantidadeDeMinas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campo -> campo.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(campo -> campo.reiniciar());
		sortearMinas();
	}
	
}
