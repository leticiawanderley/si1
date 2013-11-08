package project;

import java.util.HashMap;
import java.util.Map;

public class StringParser {
	private Map<String, String> unitMap;
	private Map<String, String> dozenMap;
	private Map<String, String> hundredMap;
	private Map<String, String> exceptionMap;
	private final String MILHOES = " milhões e ";
	private final String MILHAO = " milhao e ";
	private final String MIL = " mil e ";
	
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
	
	private String returnExtended(String number, int size, String result) {
		String numberReverse = new StringBuffer(number).reverse().toString().trim();
        String[] numberList = numberReverse.split("");
        if (size == 10){
        	result = "um bilhão";
        }
        else if (size == 9){
			String check = numberList[9] + numberList[8] + numberList[7];
			result += checkHundredExceptions(size, result, check, numberList) + MILHOES;
			this.returnExtended(number, size, result);
		}
		else if (size == 8){
			String check = numberList[8] + numberList[7];
			result += checkDozenExceptions(size, result, check, numberList)+ MILHOES;
			this.returnExtended(number, size, result);
		}
		
		else if (size == 7){
			result += unitMap.get(numberList[size--]);
			if (numberList[6].equals("1")){
				result += MILHAO;
			}
			else{
				result += MILHOES;
			}
			this.returnExtended(number, size, result);
		}
		else if (size == 6) {
			String check = numberList[6] + numberList[5] + numberList[4];
			result += checkHundredExceptions(size, result, check, numberList) + MIL;
			this.returnExtended(number, size, result);
		}
		else if (size == 5){
			String check = numberList[5] + numberList[4];
			result += checkDozenExceptions(size, result, check, numberList) + MIL;
			this.returnExtended(number, size, result);
		}
		else if (size == 4){
			if (numberList[4].equals("1")){
				result += MIL;
			}
			else{
				result += unitMap.get(numberList[size--]) + MIL;
			}
			size--;
			this.returnExtended(number, size, result);
		}
		else if (size == 3){
			String check = numberList[3] + numberList[2] + numberList[1];
			result = checkHundredExceptions(size, result, check, numberList);
		}
		else if (size == 2){
			String check = numberList[2] + numberList[1];
			result = checkDozenExceptions(size, result, check, numberList);
		}
		else if (size == 1){
			result += unitMap.get(numberList[size--]);
		}
		if (result.endsWith(" e ")){
			result = result.substring(0, result.length() - 2);
		}
		return result.trim();
	}
	
	public String result(int i){
		String number = Integer.toString(i);
		return this.returnExtended(number, number.length(), "");
	}
	
	private String checkExceptions(String number){
		if (exceptionMap.containsKey(number)){
			return exceptionMap.get(number);
		}
		return null;
	}
	
	private String checkHundredExceptions(int size, String result, String check, String[] numberList){
		if (checkExceptions(check) != null){
			result += checkExceptions(check);
			size = size - 3;
		}
		else {
			result += hundredMap.get(numberList[size--]) + " e "
					+ checkDozenExceptions(size, result, check.substring(1), numberList);			
		}
		return result;
	}
	
	private String checkDozenExceptions(int size, String result, String check, String[] numberList){
		if (checkExceptions(check) != null){
			result += checkExceptions(check);
			size = size - 2;
		}
		else {				
			result += dozenMap.get(numberList[size--]) + " e "
					+ unitMap.get(numberList[size--]);
		}
		return result;
	}
	
	private void populeUnitMap(Map<String, String> map){
		map.put("0", "zero");
		map.put("1", "um");
		map.put("2", "dois");
		map.put("3", "três");
		map.put("4", "quatro");
		map.put("5", "cinco");
		map.put("6", "seis");
		map.put("7", "sete");
		map.put("8", "oito");
		map.put("9", "nove");
	}
	
	private void populeDozenMap(Map<String, String> map){
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
	
	private void populeHundredMap(Map<String, String> map){
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
	
	private void populeExceptionsMap(Map<String, String> map){
		map.put("001", "um");
		map.put("002", "dois");
		map.put("003", "três");
		map.put("004", "quatro");
		map.put("005", "cinco");
		map.put("006", "seis");
		map.put("007", "sete");
		map.put("008", "oito");
		map.put("009", "nove");
		map.put("01", "um");
		map.put("02", "dois");
		map.put("03", "três");
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
		map.put("1000", "mil");
	}
}
