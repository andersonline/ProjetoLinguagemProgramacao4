package br.cinema.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import br.cinema.dao.AuthenticateDAO;
import br.cinema.jpa.CinemaDAOException;
import br.cinema.jpa.FabricaConexao;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login implements Initializable {

	@FXML
	private AnchorPane rootPane;

	@FXML
	private JFXTextField txtLogin;

	@FXML
	private JFXPasswordField pswSenha;

	@FXML
	private JFXButton btnFechar;

	@FXML
	private JFXButton btnEntrar;

	@FXML
	private Label lblAviso;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void validarLogin() {
		String email = txtLogin.getText();
		String password = pswSenha.getText();
		AuthenticateDAO authenticateDAO = new AuthenticateDAO();
		if (authenticateDAO.validateLogin(email, password)) {
			try {
				((Stage) rootPane.getScene().getWindow()).close();
				AnchorPane root = FXMLLoader.load(getClass().getResource("../view/ui_principal.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				scene.getStylesheets().add(getClass().getResource("../style/principal.css").toExternalForm());
				stage.setMinWidth(1140);
				stage.setMaximized(true);
				stage.setTitle("Sistema do cinema");
				stage.setScene(scene);
				stage.show();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		} else {
			showErrorMessage();
		}
	}

	@FXML
	public void closeWindow() {
		System.exit(0);
	}

	@FXML
	public void checkKey(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			if (txtLogin.getText().isEmpty()) {
				txtLogin.requestFocus();
				return;
			}
			if (pswSenha.getText().isEmpty()) {
				pswSenha.requestFocus();
				return;
			}
			validarLogin();
		}
	}

	private void showErrorMessage() {
		Timeline fadeInTimeline = new Timeline();
		KeyFrame fadeInKey = new KeyFrame(Duration.millis(250), new KeyValue(lblAviso.opacityProperty(), 1));
		fadeInTimeline.getKeyFrames().add(fadeInKey);
		fadeInTimeline.setOnFinished((v) -> {
			new Thread(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Timeline fadeOutTimeline = new Timeline();
				KeyFrame fadeOutKey = new KeyFrame(Duration.millis(500), new KeyValue(lblAviso.opacityProperty(), 0));
				fadeOutTimeline.getKeyFrames().add(fadeOutKey);
				fadeOutTimeline.setOnFinished((v2) -> {
					lblAviso.setOpacity(0);
				});
				fadeOutTimeline.play();
			}).start();
		});
		fadeInTimeline.play();
	}
}