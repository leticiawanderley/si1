package project;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que escreve os n�meros pro extenso
 * 
 * @author Leticia Wanderley
 */
public class StringParser {
	private Map<String, String> unitMap;
	
	private Map<String, String> dozenMap;
	
	private Map<String, String> hundredMap;
	
	private Map<String, String> exceptionMap;
	
	private final String MILHOES = " milh�es e ";
	
	private final String MILHAO = " um milh�o e ";
	
	private final String MIL = " mil e ";
	
	/**
	 * Construtor da classe
	 */
	public StringParser(){
		unitMap = new HashMap<String, String>();
		populeUnitMap(unitMap);
		dozenMap = new HashMap<String, String>();
		populeDozenMap(dozenMap);
		hundredMap = new HashMap<String, String>();
		populeHundredMap(hundredMap);
		exceptionMap = new HashMap<String, String>();
		populeExceptionsMap(exceptionMap);
		
	}
	
	/**
	 * Transformador dos n�meros para extenso
	 * 
	 * @param number
	 * 		N�mero a ser transformado
	 * @param size
	 * 		Tamanho do n�mero
	 * @param result
	 * 		Resultado da transforma��o
	 * @return
	 * 		O n�mero por extenso
	 */
	private String returnExtended(String number, int size, String result) {
		String numberReverse = new StringBuffer(number).reverse().toString().trim();
        String[] numberList = numberReverse.split("");
        if (size == 10){
        	result = "um bilh�o";
        }
        else if (size == 9 || size == 8 || size == 7){
			result = this.returnExtendedPart(size, number, numberList, result, MILHAO, MILHOES);
		}
		else if (size == 6 || size == 5 || size == 4){
			result = this.returnExtendedPart(size, number, numberList, result, MIL, MIL);
		}
		else if (size == 3){
			String check = numberList[3] + numberList[2] + numberList[1];
			result += checkHundredExceptions(size, check, numberList, "");
		}
		else if (size == 2){
			String check = numberList[2] + numberList[1];
			result += checkDozenExceptions(size, check, numberList, "");
		}
		else if (size == 1){
			result += unitMap.get(numberList[size--]);
		}
        
        if (result.endsWith(" e ")){
			result = result.substring(0, result.length() - 2);
		}
		return result.trim();
	}
	
	/**
	 * M�todo que retorna para o cliente o n�mero por extenso
	 * @param i
	 * 		N�mero inteiro a ser tranformado
	 * @return
	 * 		N�mero por extenso
	 */
	public String result(int i){
		String number = Integer.toString(i);
		return this.returnExtended(number, number.length(), "");
	}
	
	/**
	 * Parti��o do m�todo que transforma os inteiros em n�mero escritos por extenso 
	 * @param size
	 * 		Tamanho do n�mero
	 * @param number
	 * 		N�mero a ser transformado
	 * @param numberList
	 * 		N�mero transformado em lista
	 * @param result
	 * 		String com o resultado
	 * @param addend1
	 * 		String que representa a unidade da parti��o do n�mero, no singular
	 * @param addend2
	 * 		String que representa a casa da parti��o do n�mero, no plural
	 * @return
	 * 		Parti��o da String do resultado
	 */
	private String returnExtendedPart(int size, String number, String[] numberList, String result, String addend1, String addend2){
		if (size % 3 == 0){
			String check = numberList[size] + numberList[size - 1] + numberList[size - 2];
			result += checkHundredExceptions(size, check, numberList, addend2);
			size = size - 3;
			result = this.returnExtended(number, size, result);
		}
		else if (size == 8 || size == 5 || size == 3){
			String check = numberList[size] + numberList[size - 1];
			result += checkDozenExceptions(size, check, numberList, addend2);
			size = size - 2;
			result = this.returnExtended(number, size, result);
		}

		else if (size == 7 || size == 4 || size == 1){
			if (numberList[size].equals("1")){
				result += addend1;
			}
			else{
				result += unitMap.get(numberList[size]) + addend2;
			}
			size = size - 1;
			result = this.returnExtended(number, size, result);
		}
		return result;
	}
	
	/**
	 * Checa se o n�mero recebido faz parte do mapa de exce��es
	 * @param number
	 * 		N�mero 
	 * @return
	 * 		A String do n�mero se esta pertence ao map ou null se n�o pertence
	 */
	private String checkExceptions(String number){
		if (exceptionMap.containsKey(number)){
			return exceptionMap.get(number);
		}
		return null;
	}
	
	/**
	 * Checa se existem exce��es nas centenas
	 * @param size
	 * 		Tamanho do n�mero
	 * @param check
	 * 		Parti��o do n�mero a ser checada
	 * @param numberList
	 * 		N�mero transformado em lista
	 * @param addend
	 * 		String que representa a casa da parti��o do n�mero
	 * @return
	 * 		String da parti��o recebida
	 */
	private String checkHundredExceptions(int size, String check, String[] numberList, String addend){
		String result;
		if (check.equals("000")){
			return "";
		}
		else if (checkExceptions(check) != null){
			result = checkExceptions(check);
		}
		else {
			if (check.substring(0, 1).equals("0")){
				result = checkDozenExceptions(size--, check.substring(1), numberList, "");
			}
			else {
				result = hundredMap.get(numberList[size--]);
				if (! check.substring(1).equals("00")){
					result += " e " + checkDozenExceptions(size, check.substring(1), numberList, "");	
				}
			}
		}
		return result + addend;
	}
	
	/**
	 * Checa se existem exce��es nas dezenas
	 * @param size
	 * 		Tamanho do n�mero
	 * @param check
	 * 		Parti��o do n�mero a ser checada
	 * @param numberList
	 * 		N�mero transformado em lista
	 * @param addend
	 * 		String que representa a casa da parti��o do n�mero
	 * @return
	 * 		String da parti��o recebida
	 */
	private String checkDozenExceptions(int size, String check, String[] numberList, String addend){
		String result;
		if (check.equals("00")){
			return "";
		}
		else if (checkExceptions(check) != null){
			result = checkExceptions(check);
		}
		else {
			if (check.substring(0, 1).equals("0")){
				result = unitMap.get(numberList[size - 2]);
			}
			else {
				result = dozenMap.get(numberList[size--]); 
				if (! check.substring(1).equals("0")){
					result += " e " + unitMap.get(numberList[size--]);	
				}
			}
		}
		return result + addend;
	}
	
	/**
	 * Popula mapa com String das unidades
	 * @param map
	 * 		Mapa a ser populado
	 */
	private void populeUnitMap(Map<String, String> map){
		map.put("0", "zero");
		map.put("1", "um");
		map.put("2", "dois");
		map.put("3", "tr�s");
		map.put("4", "quatro");
		map.put("5", "cinco");
		map.put("6", "seis");
		map.put("7", "sete");
		map.put("8", "oito");
		map.put("9", "nove");
	}
	
	/**
	 * Popula mapa com String das dezenas
	 * @param map
	 * 		Mapa a ser populado
	 */
	private void populeDozenMap(Map<String, String> map){
		map.put("0", "");
		map.put("1", "dez");
		map.put("2", "vinte");
		map.put("3", "trinta");
		map.put("4", "quarenta");
		map.put("5", "cinquenta");
		map.put("6", "sessenta");
		map.put("7", "setenta");
		map.put("8", "oitenta");
		map.put("9", "noventa");
	}
	
	/**
	 * Popula mapa com String das centenas
	 * @param map
	 * 		Mapa a ser populado
	 */
	private void populeHundredMap(Map<String, String> map){
		map.put("0", "");
		map.put("1", "cento");
		map.put("2", "duzentos");
		map.put("3", "trezentos");
		map.put("4", "quatrocentos");
		map.put("5", "quinhentos");
		map.put("6", "seiscentos");
		map.put("7", "setecentos");
		map.put("8", "oitocentos");
		map.put("9", "novecentos");
	}
	
	/**
	 * Popula mapa com String das exce��es
	 * @param map
	 * 		Mapa a ser populado
	 */
	private void populeExceptionsMap(Map<String, String> map){
		map.put("001", "um");
		map.put("002", "dois");
		map.put("003", "tr�s");
		map.put("004", "quatro");
		map.put("005", "cinco");
		map.put("006", "seis");
		map.put("007", "sete");
		map.put("008", "oito");
		map.put("009", "nove");
		map.put("01", "um");
		map.put("02", "dois");
		map.put("03", "tr�s");
		map.put("04", "quatro");
		map.put("05", "cinco");
		map.put("06", "seis");
		map.put("07", "sete");
		map.put("08", "oito");
		map.put("09", "nove");
		map.put("11", "onze");
		map.put("12", "doze");
		map.put("13", "treze");
		map.put("14", "quatorze");
		map.put("15", "quinze");
		map.put("16", "dezesseis");
		map.put("17", "dezessete");
		map.put("18", "dezoito");
		map.put("19", "dezenove");
		map.put("10", "dez");
		map.put("20", "vinte");
		map.put("30", "trinta");
		map.put("40", "quarenta");
		map.put("50", "cinquenta");
		map.put("60", "sessenta");
		map.put("70", "setenta");
		map.put("80", "oitenta");
		map.put("90", "noventa");
		map.put("100", "cem");
		map.put("200", "duzentos");
		map.put("300", "trezentos");
		map.put("400", "quatrocentos");
		map.put("500", "quinhentos");
		map.put("600", "seiscentos");
		map.put("700", "setecentos");
		map.put("800", "oitocentos");
		map.put("900", "novecentos");
	}
	
	/**
	 * Verifica se o n�mero recebido � v�lido
	 * @param input
	 * 		N�mero
	 * @return
	 * 		true se o n�mero for v�lido, false caso contr�rio
	 */
	public boolean verifyInput(String input){
		 if (input.isEmpty()) {
            System.out.print("Inserir um n�mero de 0 a 1000000000: ");
            return false;
		 }
		 try {
			 Integer.parseInt(input);
		 }
		 catch (Exception e){
			 System.out.println("Inserir um n�mero de 0 a 1000000000: ");
			 return false;
		 }
		 if((long)Integer.parseInt(input.trim()) > 1000000000
				 && (long)Integer.parseInt(input.trim()) < 0){
            System.out.print("N�mero iv�lido. Tente novamente: ");
            return false;
		 }
		 return true;
	}
	
}
