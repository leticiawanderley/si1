package project;

import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {

        StringParser parser = new StringParser();

         System.out.print("Inserir um número de 0 a 1000000000: ");
         String valor = new Scanner(System.in).nextLine();

         while(!parser.verifyInput(valor)){
                 valor = new Scanner(System.in).nextLine();
         }
         
         System.out.println(parser.result(Integer.parseInt(valor.trim())));
 }
}
