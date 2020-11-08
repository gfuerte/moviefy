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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.User;

public class CreateController {
	@FXML
	private TextField username, firstName, lastName, email;
	@FXML
	private PasswordField password, retype;
	@FXML
	private Button confirm, back;
	@FXML
	private Text warning;
	
	List<User> userList = Moviefy.userList;
	HashMap<String, User> userMap = Moviefy.userMap;
	
	public void start(Stage mainStage) {
		setTextfieldFocus();
		warning.setFill(Color.RED);
		warning.setVisible(false);	
	}
	
	@FXML
	private void confirm(ActionEvent event) throws IOException {
		if(username.getText().trim().isEmpty() || firstName.getText().trim().isEmpty() || lastName.getText().trim().isEmpty()
				|| email.getText().trim().isEmpty() || password.getText().trim().isEmpty() || retype.getText().trim().isEmpty()) {
			String content = "Please Fill Out All Text Fields";
			warning.setText(content);
			warning.setVisible(true);
			return;
		}
		
		if(!password.getText().equals(retype.getText())) {
			String content = "Passwords Don't Match";
			warning.setText(content);
			warning.setVisible(true);
			password.setText("");
			retype.setText("");
			return;
		}
		
		if(email.getText().indexOf('@') == -1 && email.getText().indexOf(".co") == -1) {
			String content = "Invalid Email";
			warning.setText(content);
			warning.setVisible(true);
			email.setText("");
			return;
		}
		
		if(userMap.get(username.getText()) != null) {
			String content = "Username Not Available";
			warning.setText(content);
			warning.setVisible(true);
			username.setText("");
			return;
		}
		
		User user = new User(username.getText(), firstName.getText(), lastName.getText(), email.getText(), password.getText());
		userList.add(user);
		userMap.put(username.getText(), user);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Parent parent = (Parent) loader.load();
		
		LoginController controller = loader.getController();
		controller.setMessage("Successfully Created Account", false);
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Parent parent = (Parent) loader.load();
		
		LoginController controller = loader.getController();
		
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();	
		
		controller.start(stage);
		
		stage.setScene(scene);
		stage.show();
	}
	
	private void setTextfieldFocus() {
		username.setFocusTraversable(false);
		firstName.setFocusTraversable(false);
		lastName.setFocusTraversable(false);
		email.setFocusTraversable(false);
		password.setFocusTraversable(false);
		retype.setFocusTraversable(false);
		confirm.setFocusTraversable(false);
		back.setFocusTraversable(false);
	}
}
