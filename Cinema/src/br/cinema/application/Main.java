package br.cinema.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
//		launch(args);
		BasicConfigurator.configure();
//		Logger.getRootLogger().setLevel(Level.WARN);
		log.warn("abrindo conexão");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Cinema");
		EntityManager em = factory.createEntityManager();
	}
}
