package br.cinema.util;

public class ValidarCpf {
	public boolean cpfValido(String cpf) {
		if (cpf.length() == 11) {
			if (getDigit(cpf, 10) == (cpf.charAt(9) - 48)) {
				if (getDigit(cpf, 11) == (cpf.charAt(10) - 48)) {
					System.out.println("Validou");
					return true;
				}
			}
		}
		return false;
	}
	
	private int getDigit(String cpf, int pow) {
//		int sum = 0, lastIndex = pow - 1;
//		while (pow > 1) {
//			sum += (cpf.charAt(lastIndex) - 48) * pow--;
//		}
//		int digit = 11 - (sum % 11);
//		if (digit > 9) {
//			digit = 0;
//		}
//		return digit;
		int sum = 0;
		while (pow > 1) {
			sum += (cpf.charAt(pow - 2) - 48) * (pow--);
		}
		int digit = 11 - (sum % 11);
		if (digit > 9) {
			digit = 0;
		}
		System.out.println(digit);
		return digit;
	}
}
