package br.cinema.controller;

import java.util.regex.Pattern;

import com.jfoenix.validation.base.ValidatorBase;

import br.cinema.util.ValidarCpf;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextInputControl;

public class CPFFieldValidator extends ValidatorBase {

	public CPFFieldValidator(String message) {
		super(message);
	}

	public CPFFieldValidator() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void eval() {
		if (srcControl.get() instanceof TextInputControl) {
			evalTextInputField();
		}
	}

	private void evalTextInputField() {
		TextInputControl textField = (TextInputControl) srcControl.get();
//		ValidarCpf validarCpf = new ValidarCpf();
//		if (validarCpf.cpfValido(textField.getText())) {
//			hasErrors.set(false);
//		} else {
//			hasErrors.set(true);
//		}
		if (Pattern.matches("\\d{11}", textField.getText())) {
			int first = textField.getText().charAt(0) - 48;
			System.out.println(first);
			int soma = 0;
			for (int i = 0; i < textField.getText().length(); i++) {
				for (int j = 0; j < textField.getText().length(); j++) {
					if (textField.getText().charAt(i) - 48 != textField.getText().charAt(j)) {
						hasErrors.set(false);
						return;
					}
				}
			}
			hasErrors.set(true);
		} else {
			hasErrors.set(true);
		}
	}
}
