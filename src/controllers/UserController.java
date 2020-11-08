package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;

public class UserController {
	@FXML
	private ComboBox<String> combo;
	@FXML
	private Hyperlink home, browse, watching, completed, favorites, hold, dropped, plan, add, edit;
	@FXML
	private ImageView homeIcon, browseIcon;
	@FXML
	private ListView<String> customList;
	@FXML
	private Pane main;
	
	User user;
	
	public void start(Stage mainStage) {
		home.setBorder(Border.EMPTY);
		browse.setBorder(Border.EMPTY);
		watching.setBorder(Border.EMPTY);
		completed.setBorder(Border.EMPTY);
		favorites.setBorder(Border.EMPTY);
		hold.setBorder(Border.EMPTY);
		dropped.setBorder(Border.EMPTY);
		plan.setBorder(Border.EMPTY);
		add.setBorder(Border.EMPTY);
		edit.setBorder(Border.EMPTY);
		
		combo.getItems().removeAll(combo.getItems());
		combo.getItems().addAll("Username", "Logout");
		combo.getSelectionModel().select("Username");
		//combo.setFocusTraversable(false);

		Image iconH = new Image("/images/homeIcon.png");
		homeIcon.setImage(iconH);
		Image iconB = new Image("/images/browseIcon.png");
		browseIcon.setImage(iconB);
		
	}
	
	@FXML
	private void combo(ActionEvent event) throws IOException {
		if(combo.getValue().equals("Logout")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent parent = (Parent) loader.load();
			LoginController controller = loader.getController();
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			controller.start(stage);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	@FXML
	private void home(ActionEvent event) throws IOException {
		System.out.println("home");
		home.setVisited(false);
	}
	
	@FXML
	private void browse(ActionEvent event) throws IOException {
		System.out.println("browse");
		browse.setVisited(false);
	}
	
	@FXML
	private void watching(ActionEvent event) throws IOException {
		System.out.println("currently");
		watching.setVisited(false);
	}
	
	@FXML
	private void completed(ActionEvent event) throws IOException {
		System.out.println("completed");
		completed.setVisited(false);
	}
	
	@FXML
	private void favorites(ActionEvent event) throws IOException {
		System.out.println("favorites");
		favorites.setVisited(false);
	}
	
	@FXML
	private void hold(ActionEvent event) throws IOException {
		System.out.println("hold");
		hold.setVisited(false);
	}
	
	@FXML
	private void dropped(ActionEvent event) throws IOException {
		System.out.println("dropped");
		dropped.setVisited(false);
	}
	
	@FXML
	private void plan(ActionEvent event) throws IOException {
		System.out.println("plan");
		plan.setVisited(false);
	}
	
	@FXML
	private void add(ActionEvent event) throws IOException {
		System.out.println("add");
	}
	
	@FXML
	private void edit(ActionEvent event) throws IOException {
		System.out.println("edit");
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
