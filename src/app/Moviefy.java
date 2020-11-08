package app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Serial;
import models.User;

public class Moviefy extends Application {

	Stage mainStage;
	public static List<User> userList;
	public static HashMap<String, User> userMap = new HashMap<>();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		mainStage = primaryStage;

		/*
		Serial serial = new Serial();
		serial.getUserList().add(new User("Wycco", "Greg", "Fuerte", "wwycca@gmail.com", "password"));
		Serial.serialize(serial);		
		*/
		
		Serial serial = Serial.deserialize();
		userList = serial.getUserList();
		for(User user : userList) userMap.put(user.getUsername(), user);
			
		System.out.println("--All Users--");
		for(User user : userList) System.out.println("Username: " + user.getUsername() + "\t\tPassword: " + user.getPassword());
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/login.fxml"));
			Pane root = loader.load();
			
			LoginController controller = loader.getController();
			controller.start(primaryStage);
			
			Scene scene = new Scene(root, 600, 400);

			mainStage.setScene(scene);
			mainStage.setTitle("Moviefy");
			mainStage.setResizable(false);
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainStage.setOnCloseRequest(event -> {
			try {
				Serial.serialize(serial);
			} catch (IOException e) {}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
