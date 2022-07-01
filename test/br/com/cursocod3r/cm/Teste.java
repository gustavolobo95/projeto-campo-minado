package br.com.cursocod3r.cm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {

	@Test
	void primeiroTeste() {
		int numero = 1 + 1;
		assertEquals(2, numero);
	}
	
	@Test
	void testarSeIgualATres() {
		int x = 2 + 10 - 9;
		assertEquals(3, x);
	}
}
