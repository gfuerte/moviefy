package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import app.Moviefy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.User;

public class LoginController {
	@FXML
	private ImageView logo;
	@FXML
	private Button login;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Hyperlink create;
	@FXML
	private Text message;
	
	List<User> userList = Moviefy.userList;
	HashMap<String, User> userMap = Moviefy.userMap;
	
	public void start(Stage mainStage) {
		username.setFocusTraversable(false);
		password.setFocusTraversable(false);
		login.setFocusTraversable(false);
		
		create.setBorder(Border.EMPTY);
		
		Image image = new Image("/images/logo.png");
		logo.setImage(image);
		message.setVisible(false);
	}
	
	@FXML
	private void login(ActionEvent event) throws IOException {
		User user = userMap.get(username.getText());	
		if(user == null) {
			String content = "Username Does Not Exist";
			setMessage(content, true);
			return;
		} else if(!user.getPassword().equals(password.getText())) {
			String content = "Incorrect Password";
			setMessage(content, true);
			return;
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user.fxml"));
		Parent parent = (Parent) loader.load();
		
		UserController controller = loader.getController();
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		
		controller.start(stage);
		
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void create(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create.fxml"));
		Parent parent = (Parent) loader.load();
		
		CreateController controller = loader.getController();
		//controller.setUser(userMap.get(username.getText()));
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		
		controller.start(stage);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void setMessage(String text, boolean warning) {
		if(warning) {
			message.setText(text);
			message.setVisible(true);
			message.setFill(Color.RED);
		} else {
			message.setText(text);
			message.setVisible(true);
			message.setFill(Color.BLACK);
		}
	}
}
