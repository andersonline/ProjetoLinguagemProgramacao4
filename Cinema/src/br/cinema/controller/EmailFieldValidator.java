package br.cinema.controller;

import java.util.regex.Pattern;

import com.jfoenix.validation.base.ValidatorBase;

import br.cinema.util.ValidarCpf;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextInputControl;

public class EmailFieldValidator extends ValidatorBase {

	public EmailFieldValidator(String message) {
		super(message);
	}

	public EmailFieldValidator() {
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
		if (Pattern.matches("^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,6}$", textField.getText())) {
			hasErrors.set(false);
		} else {
			hasErrors.set(true);
		}
	}
}
