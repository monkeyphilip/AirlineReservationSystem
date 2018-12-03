package Gui;

import java.sql.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Login extends Application implements EventHandler<ActionEvent>{
	private static String user = "";
	private static String password = "";
	
	public static String getUser() {
		return user;
	}
	
	public static void setUser(String user) {
		Login.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Login.password = password;
	}
	
	public static Scene scene;
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		primaryStage.setTitle("Log In");
		primaryStage.setResizable(false);
		AnchorPane anchor = new AnchorPane();
		anchor.setPadding(new Insets(10, 10, 10, 10));

		Label loginLabel = new Label("Enter your username and password");
		loginLabel.setAlignment(javafx.geometry.Pos.CENTER);
		loginLabel.setLayoutX(144.0);
		loginLabel.setLayoutY(51.0);
		loginLabel.setPrefHeight(32.0);
		loginLabel.setPrefWidth(500.0);
		loginLabel.setText("Enter Your Username And Password");
		loginLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
		loginLabel.setFont(new Font(22.0));
		
		Label usernameLabel = new Label("Username:");
		usernameLabel.setLayoutX(189.0);
		usernameLabel.setLayoutY(131.0);
		usernameLabel.setText("Username:");
		usernameLabel.setFont(new Font(20.0));
		
		TextField userTxt = new TextField();
		userTxt.setLayoutX(311.0);
		userTxt.setLayoutY(133.0);
		userTxt.setPromptText("Username");
		
		Label passwordLabel = new Label("Password:");
		passwordLabel.setLayoutX(193.0);
		passwordLabel.setLayoutY(174.0);
		passwordLabel.setFont(new Font(20.0));

		PasswordField passwordTxt = new PasswordField();
		passwordTxt.setLayoutX(311.0);
		passwordTxt.setLayoutY(177.0);
		passwordTxt.setPromptText("Password");
		
		


	}
	
	
	

	
	
	

}


