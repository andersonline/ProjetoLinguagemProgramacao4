package br.cinema.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Filme {

    @FXML
    private TextField txtTitulo;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtDuracao;

    @FXML
    private ComboBox<?> cboClassificacao;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextArea txaSinopse;
}