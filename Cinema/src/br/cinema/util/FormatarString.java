package br.cinema.util;

public class FormatarString implements Formatar {
	
	/**
	 * @param num contendo a string a ser formatada
	 * @param mascara que exibira a string formatada
	 * 		ex.: ###.###.###-##
	 */
	@Override
	public String formatar(String num, String mascara) {
		int iNum = 0;
		StringBuilder numFormatado = new StringBuilder();
		for (int iMask = 0; iMask < mascara.length(); iMask++) {
			char numAtual = mascara.charAt(iMask);
			if (numAtual == '#') {
				if (iNum < num.length()) {
					numFormatado.append(num.charAt(iNum++));
				} else {
					return "INVÁLIDO";
				}
			} else {
				numFormatado.append(numAtual);
			}
		}
		if (iNum != num.length()) {
			return "INVÁLIDO";
		}
		return numFormatado.toString();
	}
}
