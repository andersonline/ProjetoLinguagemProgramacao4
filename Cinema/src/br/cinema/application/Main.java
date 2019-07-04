package br.cinema.application;

import java.time.LocalDate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import br.cinema.dao.AuthenticateDAO;
import br.cinema.dao.DaoGenerico;
import br.cinema.model.Cliente;
import br.cinema.model.Endereco;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	private double posX;
	private double posY;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/ui_login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../style/login.css").toExternalForm());
			scene.setFill(Color.TRANSPARENT);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
			
			root.setOnMousePressed((event) -> {
				posX = event.getSceneX();
				posY = event.getSceneY();
			});
			root.setOnMouseDragged((event) -> {
				root.setCursor(Cursor.CLOSED_HAND);
				stage.setX(event.getScreenX() - posX);
				stage.setY(event.getScreenY() - posY);
			});
			root.setOnMouseReleased((event) -> {
				root.setCursor(Cursor.DEFAULT);
			});
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	public static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		launch(args);
//		BasicConfigurator.configure();
//		Logger.getRootLogger().setLevel(Level.WARN);
//		log.warn("abrindo conexão");
//		DaoGenerico<Endereco> daoEndereco = new DaoGenerico<>();
//		Endereco endereco = new Endereco("Rua teste", "12", "Jardim", "0987678", "TL", "MS", null);
//		daoEndereco.save(endereco);
//		DaoGenerico<Cliente> daoCliente = new DaoGenerico<>();
//		daoCliente.save(new Cliente("Anderson", "09876543210", "anderson@gmail.com", "123", "67998765432", LocalDate.of(2019, 12, 19), endereco, "vip", false, null));
		
//		AuthenticateDAO authenticateDAO = new AuthenticateDAO();
//		log.warn(authenticateDAO.validateLogin("anderson@gmail.com", "123"));
	}
}
