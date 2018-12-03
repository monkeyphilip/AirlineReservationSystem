package Gui;

import java.sql.*;

import Objects.Flights;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;



public class MainPage extends Application implements EventHandler<ActionEvent> {
	
	private ObservableList<ObservableList> data;
	private String usernameID = "";
	
	private boolean isAdmin = false;
	

// Main Method	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Home");
		primaryStage.setResizable(false);
		AnchorPane anchor = new AnchorPane();
		anchor.setPadding(new Insets(10, 10, 10, 10));
		
		TableView<Flights> table = new TableView();
		final ObservableList<Flights> data = FXCollections.observableArrayList();
		
		Label userID = new Label();
		Button searchFlights = new Button();
		Label myFlights = new Label();
		Button deleteFlights = new Button();
		Button logOut = new Button();
		TextField deleteFlightTxt = new TextField();
		Button refresh = new Button("Refresh");
		
		try {
			
		}
		
		catch (Exception exe) {
			
		}
		
		userID.setAlignment(javafx.geometry.Pos.CENTER);
		userID.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		userID.setLayoutX(970.0);
		userID.setLayoutY(20.0);
		userID.setText("Logged in as: " + Login.getUser() );
		userID.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
		userID.setFont(new Font(18.0));
		
		searchFlights.setLayoutX(1100.0);
		searchFlights.setLayoutY(214.0);
		searchFlights.setMnemonicParsing(false);
		searchFlights.setText("Search Flights");
		searchFlights.setOnAction(e -> {
			SearchPage search = new SearchPage();
			try {
				search.start(primaryStage);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		
		
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUsernameID() {
		return usernameID;
	}

	public void setUsernameID(String usernameID) {
		this.usernameID = usernameID;
	}
	
	

}
