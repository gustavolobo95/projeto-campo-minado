package br.com.cursocod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.cursocod3r.cm.excecao.ExplosaoException;

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
			campos.get(aleatorio.nextInt(0, campos.size())).minar();
			minasArmadas = campos.stream()
					.filter(campo -> campo.isMinado())
					.count();
		} while(minasArmadas < quantidadeDeMinas);
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campo -> campo.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(campo -> campo.reiniciar());
		sortearMinas();
	}
	
	public void reiniciarSemSortearMinas() {
		campos.stream().forEach(campo -> campo.reiniciar());
	}
	
	public void abrirCampo(int linha, int coluna) {
		try {
			campos.stream()
			.filter(campo -> 
			campo.getLinha() == linha && campo.getColuna() == coluna)
			.findFirst()
			.ifPresent(campo -> campo.abrir());;	
		} catch(ExplosaoException explosao) {
			campos.forEach(campo -> campo.setAberto(true));
			throw explosao;
		}
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.stream()
				.filter(campo ->
				campo.getLinha() == linha && campo.getColuna() == coluna)
				.findFirst()
				.ifPresent(campo -> campo.alternarMarcacao());
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		for(int linhaAtual = 0; linhaAtual < quantidadeDeLinhas; linhaAtual++) {
			for(int colunaAtual = 0; colunaAtual < quantidadeDeColunas; colunaAtual++) {
				stringBuilder.append(" ");
				stringBuilder.append(campos.get(i));
				stringBuilder.append(" ");
				i++;
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public long contarCampos() {
		return campos.stream().count();
	}
	
	public Campo getCampo(int linha, int coluna) {
		return campos.stream()
						.filter(campo -> campo.getLinha() == linha && campo.getColuna() == coluna)
						.findFirst()
						.get();
	}
	
}
