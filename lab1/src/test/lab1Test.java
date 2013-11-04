package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project.Lab1;

public class lab1Test {
	Lab1 lab;
	
	@Before
	public void setUp() throws Exception {
		lab = new Lab1();
	}

	@Test
	public void casoMaisSimplesTest() {
		assertEquals("um", lab.casoMaisSimples(1));
		assertEquals("dez", lab.casoMaisSimples(10));
		assertEquals("zero", lab.casoMaisSimples(0));
		assertEquals("cinco", lab.casoMaisSimples(5));
	}
	
	@Test
	public void casoSimplesTest() {
		assertEquals("quinze", lab.casoSimples(15));
		assertEquals("mil", lab.casoSimples(1000));
		assertEquals("vinte", lab.casoSimples(20));
		assertEquals("setenta", lab.casoSimples(70));
		assertEquals("dezoito", lab.casoSimples(18));
	}

}
