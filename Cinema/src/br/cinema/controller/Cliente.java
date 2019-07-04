package br.cinema.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import br.cinema.dao.DaoGenerico;
import br.cinema.model.Endereco;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Cliente {

	private List<String> siglasEstados;
	private boolean isStudent = false;

	@FXML
	private ScrollPane rootPane;

	@FXML
	private HBox savePane;

	@FXML
	private HBox hidePane;

	@FXML
	private JFXComboBox<String> cboTipo;

	@FXML
	private JFXRadioButton rbtEstudante;

	@FXML
	private JFXDatePicker dtpValidade;

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCpf;

	@FXML
	private JFXDatePicker dtpNascimento;

	@FXML
	private JFXTextField txtTelefone;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtCep;

	@FXML
	private JFXTextField txtLogradouro;

	@FXML
	private JFXTextField txtNumero;

	@FXML
	private JFXTextField txtBairro;

	@FXML
	private JFXTextField txtCidade;

	@FXML
	private JFXComboBox<String> cboEstado;

	@FXML
	private JFXTextArea txaComplemento;

	@FXML
	private JFXButton btnSave;

	@FXML
	private HBox hbxAlert;

	public void initialize() {
		System.out.println("id: " + Principal.atualizar.getId());
		ObservableList<String> estados = FXCollections.observableArrayList("Acre", "Alagoas", "Amapá", "Amazonas",
				"Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso",
				"Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí",
				"Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina",
				"São Paulo", "Sergipe", "Tocantins");
		String temp[] = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
				"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
		siglasEstados = new LinkedList<String>(Arrays.asList(temp));
		cboEstado.setItems(estados);
		cboTipo.setItems(FXCollections.observableArrayList("VIP", "Gold", "Diamante", "Safira"));
		RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Campo é obrigatório");
		CPFFieldValidator cpfFieldValidator = new CPFFieldValidator("CPF inválido");
		EmailFieldValidator emailFieldValidator = new EmailFieldValidator("E-mail inválido");
		txtNome.setValidators(requiredFieldValidator);
		txtCpf.setValidators(requiredFieldValidator, cpfFieldValidator);
		dtpNascimento.setValidators(requiredFieldValidator);
		txtTelefone.setValidators(requiredFieldValidator);
		txtEmail.setValidators(requiredFieldValidator, emailFieldValidator);
		txtLogradouro.setValidators(requiredFieldValidator);
		txtNumero.setValidators(requiredFieldValidator);
		txtBairro.setValidators(requiredFieldValidator);
		txtCidade.setValidators(requiredFieldValidator);
		cboEstado.setValidators(requiredFieldValidator);
		rbtEstudante.setOnAction((o) -> {
			if (!isStudent) {
				dtpValidade.setDisable(false);
				dtpValidade.setValidators(requiredFieldValidator);
				isStudent = true;
			} else {
				dtpValidade.setDisable(true);
				dtpValidade.setValue(LocalDate.now());
				dtpValidade.validate();
				dtpValidade.getEditor().setText(null);
				isStudent = false;
			}
		});
		dtpValidade.setDisable(true);
		if (Principal.atualizar.getId() != 0) {
			txtNome.setText(Principal.atualizar.getNome());
			txtCpf.setText(Principal.atualizar.getCpf());
			dtpNascimento.setValue(Principal.atualizar.getDataNascimento());
			txtTelefone.setText(Principal.atualizar.getFone());
			txtEmail.setText(Principal.atualizar.getEmail());
			cboTipo.setValue(Principal.atualizar.getTipoCliente());
			if (Principal.atualizar.getValidade() != null) {
				rbtEstudante.selectedProperty().set(true);
				dtpValidade.setValue(Principal.atualizar.getValidade());
				dtpValidade.setDisable(false);
				isStudent = true;
			} else {
				rbtEstudante.selectedProperty().set(false);
				dtpValidade.setDisable(true);
			}
			txtCep.setText(Principal.atualizar.getEndereco().getCep());
			txtLogradouro.setText(Principal.atualizar.getEndereco().getLogradouro());
			txtNumero.setText(Principal.atualizar.getEndereco().getNumero());
			txtBairro.setText(Principal.atualizar.getEndereco().getBairro());
			txtCidade.setText(Principal.atualizar.getEndereco().getCidade());
			cboEstado.setValue(estados.get(siglasEstados.indexOf(Principal.atualizar.getEndereco().getEstado())));
			txaComplemento.setText(Principal.atualizar.getEndereco().getComplemento());
		}
	}

	@FXML
	private void validateFields() {
		if (!txtNome.validate()) {
			return;
		}
		if (!txtCpf.validate()) {
			return;
		}
		if (!dtpNascimento.validate()) {
			return;
		}
		if (!txtEmail.validate()) {
			return;
		}
		if (!cboTipo.validate()) {
			return;
		}
		if (!txtLogradouro.validate()) {
			return;
		}
		if (!txtNumero.validate()) {
			return;
		}
		if (!txtBairro.validate()) {
			return;
		}
		if (!txtCidade.validate()) {
			return;
		}
		if (!cboEstado.validate()) {
			return;
		}
		if (!dtpValidade.validate()) {
			return;
		}
		if (Principal.atualizar.getId() != 0) {
			// Editar
			DaoGenerico<Endereco> daoEndereco = new DaoGenerico<>();
			Endereco endereco = new Endereco(Principal.atualizar.getEndereco().getId(), txtLogradouro.getText(),
					txtNumero.getText(), txtBairro.getText(), txtCep.getText(), txtCidade.getText(),
					siglasEstados.get(cboEstado.getSelectionModel().getSelectedIndex()), txaComplemento.getText());
			daoEndereco.update(endereco);
			DaoGenerico<br.cinema.model.Cliente> daoCliente = new DaoGenerico<>();
			daoCliente.update(new br.cinema.model.Cliente(Principal.atualizar.getId(), txtNome.getText(),
					txtCpf.getText(), txtEmail.getText(), Principal.atualizar.getSenha(), txtTelefone.getText(), dtpNascimento.getValue(),
					endereco, cboTipo.getValue(), !dtpValidade.isDisabled(), dtpValidade.getValue()));
		} else {
			// Novo
			DaoGenerico<Endereco> daoEndereco = new DaoGenerico<>();
			Endereco endereco = new Endereco(txtLogradouro.getText(), txtNumero.getText(), txtBairro.getText(),
					txtCep.getText(), txtCidade.getText(),
					siglasEstados.get(cboEstado.getSelectionModel().getSelectedIndex()), txaComplemento.getText());
			daoEndereco.save(endereco);
			DaoGenerico<br.cinema.model.Cliente> daoCliente = new DaoGenerico<>();
			if (daoCliente.save(new br.cinema.model.Cliente(txtNome.getText(), txtCpf.getText(), txtEmail.getText(),
					null, txtTelefone.getText(), dtpNascimento.getValue(), endereco, cboTipo.getValue(),
					!dtpValidade.isDisabled(), dtpValidade.getValue()))) {
				hbxAlert.setVisible(true);
			} else {
				System.out.println("Erro");
			}
		}
	}

	private void showErrorMessage() {
		hbxAlert.setVisible(true);
	}
}
