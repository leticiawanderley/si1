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
	public void casoSimplesTest() {
		assertEquals("um", lab.casoSimples(1));
		assertEquals("dez", lab.casoSimples(10));
		assertEquals("zero", lab.casoSimples(0));
		assertEquals("cinco", lab.casoSimples(5));
	}

}
