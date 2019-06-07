package br.cinema.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class OperacoesCliente {

    @FXML
    private JFXButton btnNovo;

    @FXML
    private JFXComboBox<String> cboPor;

    @FXML
    private JFXTextField txtBuscar;

    @FXML
    private JFXButton btnBuscar;
    
    @FXML
    private TableView<String> tblClientes;

    @FXML
    private JFXRadioButton rbtSelecionarTodos;

    @FXML
    private JFXButton btnGerarRelatorio;

    @FXML
    private JFXButton btnExcluir;
    
    public void initialize() {
    	ObservableList<String> olv = FXCollections.<String>observableArrayList("CPF", "E-mail", "Fone", "Data de nascimento", "Tipo do cliente");
		cboPor.getItems().addAll(olv);
    }
    
    @FXML
    public void novoCliente() {
    	Principal.novo = true;
    }
}
