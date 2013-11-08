package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import project.StringParser;

public class StringParserTest {
	StringParser parser;
	
	@Before
	public void setUp() throws Exception {
		parser = new StringParser();
	}

	@Test
	public void unitParserTest() {
		assertEquals("zero", parser.result(0));
		assertEquals("um", parser.result(1));
		assertEquals("dois", parser.result(2));
		assertEquals("três", parser.result(3));
		assertEquals("quatro", parser.result(4));
		assertEquals("cinco", parser.result(5));
		assertEquals("seis", parser.result(6));
		assertEquals("sete", parser.result(7));
		assertEquals("oito", parser.result(8));
		assertEquals("nove", parser.result(9));
		assertEquals("dez", parser.result(10));
	}
	
	@Test
	public void dozenParserTest() {
		assertEquals("onze", parser.result(11));
		assertEquals("doze", parser.result(12));
		assertEquals("quinze", parser.result(15));
		assertEquals("dezesseis", parser.result(16));
		assertEquals("dezoito", parser.result(18));
		assertEquals("vinte", parser.result(20));
		assertEquals("setenta", parser.result(70));
		assertEquals("noventa", parser.result(90));
	}
	
	@Test
	public void oneWordnumberTest(){
		assertEquals("cem", parser.result(100));
		assertEquals("duzentos", parser.result(200));
		assertEquals("seiscentos", parser.result(600));
		assertEquals("oitocentos", parser.result(800));
		assertEquals("mil", parser.result(1000));
	}
	
	@Test
	public void twoWordsNumbersTest(){
		assertEquals("vinte e um", parser.result(21));
		assertEquals("trinta e sete", parser.result(37));
		assertEquals("sessenta e oito", parser.result(68));
		assertEquals("cento e um", parser.result(101));
		assertEquals("quinhentos e sessenta", parser.result(560));
		assertEquals("oitocentos e dois", parser.result(802));
		//assertEquals("mil e cinco", parser.result(1005));
		//assertEquals("dois mil", parser.result(2000));
		//assertEquals("um milhão", parser.result(1000000));
		//assertEquals("um bilhão", parser.result(1000000000));
	}
	
	public void threeWordsNumbersTest(){
		
	}

}
