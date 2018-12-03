package Gui;

import java.sql.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
		usernameLabel.setLayoutX(267.0);
		usernameLabel.setLayoutY(131.0);
		usernameLabel.setText("Username:");
		usernameLabel.setFont(new Font(20.0));
		
		TextField userTxt = new TextField();
		userTxt.setLayoutX(370.0);
		userTxt.setLayoutY(133.0);
		userTxt.setPromptText("Username");
		
		Label passwordLabel = new Label("Password:");
		passwordLabel.setLayoutX(270.0);
		passwordLabel.setLayoutY(174.0);
		passwordLabel.setFont(new Font(20.0));

		PasswordField passwordTxt = new PasswordField();
		passwordTxt.setLayoutX(370.0);
		passwordTxt.setLayoutY(177.0);
		passwordTxt.setPromptText("Password");
		passwordTxt.setOnAction(e -> {
			try {
				
			}
			catch (Exception ex) {
				
			}
		});
		
		
		Button login = new Button("Log In");
		login.setLayoutX(370.0);
		login.setLayoutY(222.0);
		login.setMnemonicParsing(false);
		login.setPrefHeight(25.0);
		login.setPrefWidth(149.0);
		login.setText("Log In");
		
		login.setOnAction(e -> {
			try {
				
			}
			catch(Exception ex) {
				
			}
		});
		
		Button register = new Button("Register");
		register.setLayoutX(370.0);
		register.setLayoutY(255.0);
		register.setMnemonicParsing(false);
		register.setPrefHeight(25.0);
		register.setPrefWidth(149.0);
		register.setOnAction(e -> {
			try {
				
			}
			catch(Exception ex) {
				
			}
		});
		
		Button exit = new Button("Exit");
		exit.setLayoutX(370.0);
		exit.setLayoutY(328.0);
		exit.setMnemonicParsing(false);
		exit.setPrefHeight(25.0);
		exit.setPrefWidth(150.0);
		exit.setOnAction(e -> {
			System.exit(0);
		});
		
		exit.setMinWidth(150);
		register.setMinWidth(150);
		login.setMinWidth(150);
		
		anchor.getChildren().addAll(userTxt, passwordTxt, login, register, usernameLabel, exit,
				loginLabel, passwordLabel);
		scene = new Scene(anchor, 825, 500);
		;
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();



	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}

