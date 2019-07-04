package br.cinema.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JCheckBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import br.cinema.dao.DaoGenerico;
import br.cinema.model.Cliente;
import br.cinema.util.Selecionados;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

public class OperacoesCliente {

	@FXML
	private JFXButton btnNovo;
	
	@FXML
	private JFXButton btnEditar;

	@FXML
	private JFXComboBox<String> cboPor;

	@FXML
	private JFXTextField txtBuscar;

	@FXML
	private JFXButton btnBuscar;

	@FXML
	private TableView<br.cinema.model.Cliente> tblClientes;

	@FXML
	private TableColumn<br.cinema.model.Cliente, Boolean> selector;

	@FXML
	private TableColumn<br.cinema.model.Cliente, String> nome;

	@FXML
	private TableColumn<br.cinema.model.Cliente, String> cpf;

	@FXML
	private TableColumn<br.cinema.model.Cliente, String> email;

	@FXML
	private TableColumn<br.cinema.model.Cliente, String> plano;

	@FXML
	private JFXRadioButton rbtSelecionarTodos;

	@FXML
	private JFXButton btnGerarRelatorio;

	@FXML
	private JFXButton btnExcluir;

	public void initialize() {
    	ObservableList<String> olv = FXCollections.<String>observableArrayList("CPF", "E-mail", "Fone", "Data de nascimento", "Tipo do cliente");
		cboPor.getItems().addAll(olv);
		DaoGenerico<br.cinema.model.Cliente> dao = new DaoGenerico<>();
//		for (br.cinema.model.Cliente c : dao.getAll(br.cinema.model.Cliente.class)) {
//			tblClientes.getItems().add(c);
//		}
		selector.setCellValueFactory((TableColumn.CellDataFeatures<br.cinema.model.Cliente, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
		selector.setCellFactory((TableColumn<br.cinema.model.Cliente, Boolean> p) -> new BooleanCell());
		nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		plano.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
		tblClientes.setItems(FXCollections.observableArrayList(dao.getAll(br.cinema.model.Cliente.class)));
		tblClientes.setRowFactory( tv -> {
		    TableRow<br.cinema.model.Cliente> row = new TableRow<>();
		    row.setCursor(Cursor.HAND);
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
		        	Principal.atualizar = row.getItem();
		            btnEditar.fire();
		        }
		    });
		    return row ;
		});
    }

	@FXML
	public void novoCliente() {
		Principal.novo = true;
	}
	
	@FXML
	public void editarCliente() {
		Principal.editar = true;
	}
	
	@FXML
	public void deleteClientes() {
		DaoGenerico<br.cinema.model.Cliente> dao = new DaoGenerico<>();
		for (br.cinema.model.Cliente c : Selecionados.list) {
			dao.delete(br.cinema.model.Cliente.class, c.getId());
		}
		tblClientes.setItems(FXCollections.observableArrayList(dao.getAll(br.cinema.model.Cliente.class)));
		dao.close();
	}
}

class BooleanCell extends TableCell<br.cinema.model.Cliente, Boolean> {
	private JFXCheckBox checkBox;

	public BooleanCell() {
		checkBox = new JFXCheckBox();
		checkBox.setCheckedColor(Paint.valueOf("#009688"));
		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
//				if (isEditing())
			}
		});
		this.setGraphic(checkBox);
		this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		this.setEditable(true);
	}

	@Override
	public void startEdit() {
		super.startEdit();
		if (isEmpty()) {
			return;
		}
		checkBox.setDisable(false);
		checkBox.requestFocus();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		checkBox.setDisable(true);
	}

	@Override
	public void updateItem(Boolean item, boolean empty) {
		super.updateItem(item, empty);
		checkBox.setDisable(empty);
		checkBox.setVisible(!empty);
		if (!isEmpty()) {
			checkBox.selectedProperty().addListener((ov, t1, t2) -> {
				br.cinema.model.Cliente cli = (br.cinema.model.Cliente) this.getTableRow().getItem();
				if (checkBox.isSelected()) {
					Selecionados.list.add(cli);
				} else {
					Selecionados.list.remove(cli);
				}
			});
		}
	}
}