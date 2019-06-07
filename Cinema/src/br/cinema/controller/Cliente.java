package br.cinema.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class Cliente {
	
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
}
