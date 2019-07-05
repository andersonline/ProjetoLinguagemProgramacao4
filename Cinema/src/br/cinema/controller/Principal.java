package br.cinema.controller;

import java.io.IOException;

import br.cinema.model.Cliente;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class Principal {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TabPane tbpCliente;

	@FXML
	private TabPane tbpOperacoes;
	
	@FXML
	private ListView<String> lstCategorias;

	private Tab tabCliente = new Tab();
	private Tab tabOperacoes = new Tab();
	public static boolean novo = false;
	public static boolean editar = false;
	public static br.cinema.model.Cliente atualizar = new Cliente();

	public void initialize() {
		loadListView();
		selectItem();
	}

	private void loadListView() {
		ObservableList<String> olv = FXCollections.<String>observableArrayList("Clientes", "Funcionários", "Filmes",
				"Sessões", "Pagamentos", "Relatórios");

		lstCategorias.getItems().addAll(olv);
		lstCategorias.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item);
							setFont(Font.font(16));
//                            setTextFill(Color.WHITE);
//                            setBackground(new Background(new BackgroundFill(Color.rgb(39, 52, 59), CornerRadii.EMPTY, new Insets(0, 0, 1, 0))));
						}
					}
				};
			}
		});
		lstCategorias.setFixedCellSize((545) / lstCategorias.getItems().size());
	}

	private void selectItem() {
		lstCategorias.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				int itemIndex = lstCategorias.getSelectionModel().getSelectedIndex();
				switch (itemIndex) {
				case 0:
					try {
						Node telaCliente = (AnchorPane) FXMLLoader
								.load(getClass().getResource("../view/ui_cliente.fxml"));
						tabCliente.setContent(telaCliente);
						tbpCliente.getTabs().setAll(tabCliente);
						Node telaOperacoes = (VBox) FXMLLoader
								.load(getClass().getResource("../view/ui_operacoes_cliente.fxml"));
						// Novo cliente
						((Button) telaOperacoes.lookup("#btnNovo")).setOnAction(event -> {
							novoCliente();
						});
						((Button) telaOperacoes.lookup("#btnEditar")).setOnAction(event -> {
							editarCliente();
						});
						// 
						tabOperacoes.setContent(telaOperacoes);
						tbpOperacoes.getTabs().setAll(tabOperacoes);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 1:
					try {
						Node node = (HBox) FXMLLoader.load(getClass().getResource("../view/ui_funcionario.fxml"));
						tabCliente.setContent(node);
						tbpCliente.getTabs().setAll(tabCliente);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				default:
					break;
				}
			}

		});
	}

	protected void novoCliente() {
		try {
			Principal.atualizar = new Cliente();
			Node telaLimpa = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/ui_cliente.fxml"));
			tabCliente.setContent(telaLimpa);
			telaLimpa.lookup("#rootPane").setEffect(null);
			telaLimpa.lookup("#hidePane").setVisible(false);
			telaLimpa.lookup("#savePane").setVisible(true);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	protected void editarCliente() {
		try {
			Node telaLimpa = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/ui_cliente.fxml"));
			tabCliente.setContent(telaLimpa);
			telaLimpa.lookup("#rootPane").setEffect(null);
			telaLimpa.lookup("#hidePane").setVisible(false);
			telaLimpa.lookup("#savePane").setVisible(true);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
