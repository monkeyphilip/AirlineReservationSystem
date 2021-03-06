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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;



public class MainPage extends Application implements EventHandler<ActionEvent> {
	
	private ObservableList<ObservableList> data;
	private String usernameID = "";
	
	private boolean isAdmin = false;
	
	public String getUsernameId() {
		return usernameID;
	}

	public void setUsernameId(String usernameId) {
		this.usernameID = usernameId;
	}
	
	RegisterFlights flightRegister =new RegisterFlights();

	UpdateFlights updateFlights = new UpdateFlights();
	


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
			Connection myConn;
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airlinedatabase", "root",
					"confident");

			String sqlUserCheck = "SELECT * FROM `airlinedatabase`.`Users` where username = '" + Login.getUser()
					+ "' and isAdmin = '1'";
			
			Statement myStat = myConn.createStatement();
			
			ResultSet myRs;
			myRs = myStat.executeQuery(sqlUserCheck);

			
			int count = 0;
			while (myRs.next()) {
				count = count + 1;

			}

			if (count > 0) {
				setAdmin(true);
			}

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
				
			} catch (Exception ex) {
								
			}

		});
		
		table.setLayoutX(10.0);
		table.setLayoutY(57.0);
		table.setPrefHeight(329.0);
		table.setPrefWidth(1031.11);

		myFlights.setLayoutX(420.0);
		myFlights.setLayoutY(10.0);
		myFlights.setText("My Flights");
		myFlights.setFont(new Font(25.0));

		refresh.setLayoutX(1100.0);
		refresh.setLayoutY(179.0);
		refresh.setMnemonicParsing(false);
		refresh.setOnAction(e -> { 
			try {
				Connection myConn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/airlinedatabase", "root",
						"confident");
			String sqlUserCheck = "select  `num`, `airline`, `origin_city`, `destination_city`, `departure_time`, `arrival_time`, `departure_date`, `arrival_date` from\r\n"
					+ "airlinedatabase.Flights inner Join airlinedatabase.FlightUser\r\n" + "on flight_id = Flights.num\r\n"
					+ "inner join airlinedatabase.Users on FlightUser.user_id = Users.ssn where username = '"
					+ Login.getUser() + "'";
			
			PreparedStatement myStat = myConn.prepareStatement(sqlUserCheck);
			
			ResultSet myRs;
			myRs = myStat.executeQuery();
			table.getItems().clear();

			

			while (myRs.next()) {

				data.add(new Flights(myRs.getInt("num"), myRs.getString("airline"), myRs.getString("origin_city"),
						myRs.getString("destination_city"), myRs.getDate("departure_date"),
						myRs.getTime("departure_time"), myRs.getDate("arrival_date"),
						myRs.getTime("arrival_time")));
				table.setItems(data);
			}
			myStat.close();
			myRs.close();
			
		} catch (Exception ex) {
			System.out.print(ex);

		}

	});
		
		logOut.setLayoutX(1100.0);
		logOut.setLayoutY(249.0);
		logOut.setMnemonicParsing(false);
		logOut.setText("Log Out");
		logOut.setOnAction(e -> {
			Login loginPage = new Login();
			try {
				loginPage.start(primaryStage);
				
			} catch (Exception ex) {
				
			}
		});
		
		try {
			Connection myConn;
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airlinedatabase", "root",
					"confident");

			String sqlUserCheck = "SELECT * FROM `airlinedatabase`.`Users` where username = '" + Login.getUser() + "'";
			
			Statement myStat = myConn.createStatement();
			
			ResultSet myRs;
			myRs = myStat.executeQuery(sqlUserCheck);

			
			int count = 0;
			while (myRs.next()) {
				count = count + 1;
				setUsernameId(myRs.getString("ssn"));

			}

		} catch (Exception ex) {
			System.out.print(ex);

		}
		
		Label deleteFlightLbl = new Label("Enter Flight Number to Delete:");
		deleteFlightLbl.setLayoutX(1065);
		deleteFlightLbl.setLayoutY(89);

		deleteFlightTxt.setLayoutX(1100);
		deleteFlightTxt.setLayoutY(109);
		deleteFlightTxt.setPrefHeight(25);
		deleteFlightTxt.setPrefWidth(90);

		deleteFlights.setLayoutX(1100.0);
		deleteFlights.setLayoutY(139.0);
		deleteFlights.setMnemonicParsing(false);
		deleteFlights.setText("Delete Flight");
		deleteFlights.setOnAction(e -> {
			try {

				Connection myConn;
				myConn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/airlinedatabase", "root",
						"confident");

				String sqlFlightDelete = "Delete FROM airlinedatabase.FlightUser where flight_id= '"
						+ deleteFlightTxt.getText().trim() + "' and From FlightUser.user_id= '" + getUsernameID() + "'";
				String sqlFlightCheck = "SELECT `flight_id`, `user_id` FROM `FlightUser` where user_id = '"
						+ getUsernameID() + "' and flight_id= '" + deleteFlightTxt.getText().trim() + "'";
			
				Statement myStat = myConn.createStatement();
				
				ResultSet myRs;
				myRs = myStat.executeQuery(sqlFlightCheck);

				int count = 0;
				while (myRs.next()) {
					count = count + 1;
					setUsernameId(myRs.getString("user_id"));
				}

				if (count > 0) {
					myStat.executeUpdate(sqlFlightDelete);

				}

				else {
					AlertBox.display("Error!",
							"Error! you have not  booked flight number: " + deleteFlightTxt.getText().trim()
									+ " yet. \n You cannot delete a flight you havent booked!");
				}
				myStat.close();
				myRs.close();
				myConn.close();
			}

			catch (SQLException ex) {
				System.out.print(ex);
			
			}

		});
		TableColumn<Flights, Integer> column1 = new TableColumn<Flights, Integer>("Flight Number");
		column1.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
		column1.setMinWidth(128.88);

		TableColumn<Flights, String> column2 = new TableColumn<Flights, String>("Airline");
		column2.setCellValueFactory(new PropertyValueFactory<>("Airline"));
		column2.setMinWidth(128.88);

		TableColumn<Flights, String> column3 = new TableColumn<Flights, String>("Origin City");
		column3.setCellValueFactory(new PropertyValueFactory<>("originCity"));
		column3.setMinWidth(128.88);

		TableColumn<Flights, String> column4 = new TableColumn<Flights, String>("Destination City");
		column4.setCellValueFactory(new PropertyValueFactory<>("destinationCity"));
		column4.setMinWidth(128.88);

		TableColumn<Flights, Date> column5 = new TableColumn<Flights, Date>("Departure Date");
		column5.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
		column5.setMinWidth(128.88);

		TableColumn<Flights, Time> column6 = new TableColumn<Flights, Time>("Departure Time");
		column6.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		column6.setMinWidth(128.88);

		TableColumn<Flights, Date> column7 = new TableColumn<Flights, Date>("Arrival Date");
		column7.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		column7.setMinWidth(128.88);

		TableColumn<Flights, Time> column8 = new TableColumn<Flights, Time>("Arrival Time");
		column8.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		column8.setMinWidth(128.88);
		
		

		table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
		try {
			Connection myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airlinedatabase", "root",
					"confident");
			String sqlUserCheck = "select  `num`, `airline`, `origin_city`, `destination_city`, `departure_time`, `arrival_time`, `departure_date`, `arrival_date` from\r\n"
					+ "airlinedatabase.Flights inner Join airlinedatabase.FlightUser\r\n" + "on flight_id = Flight.num\r\n"
					+ "inner join airlinedatabase.Users on FlightUser.user_id = Users.ssn where username = '" + Login.getUser()
					+ "'";
		
			PreparedStatement myStat = myConn.prepareStatement(sqlUserCheck);
			
			ResultSet myRs;
			myRs = myStat.executeQuery();
			table.getItems().clear();

			

			while (myRs.next()) {

				data.add(new Flights(myRs.getInt("num"), myRs.getString("airline"), myRs.getString("origin_city"),
						myRs.getString("destination_city"), myRs.getDate("departure_date"),
						myRs.getTime("departure_time"), myRs.getDate("arrival_date"), myRs.getTime("arrival_time")));
				table.setItems(data);
			}
			myStat.close();
			myRs.close();
		} catch (Exception ex) {

		}

		
		Button adminTool = new Button("Admin Add flight");
		adminTool.setLayoutX(1100);
		adminTool.setLayoutY(290);
		adminTool.setOnAction(e ->{
			
			try {
				flightRegister.start(primaryStage);
				
				
			} catch (Exception ex) {
				
				System.out.print(ex);
				
			}
		});
		
		Button adminTool1 = new Button("Admin Flight Edit/Delete");
		adminTool1.setLayoutX(1100);
		adminTool1.setLayoutY(330);
		adminTool1.setOnAction(e -> {
			
			try {
				updateFlights.start(primaryStage);
				
			} catch (Exception ex) {
			
			}
		});
		
		if (isAdmin() == true) {
			anchor.getChildren().add(adminTool);
			anchor.getChildren().add(adminTool1);
		}
		
		anchor.getChildren().addAll(deleteFlightLbl, userID, searchFlights, table, myFlights, deleteFlights,
				deleteFlightTxt, logOut, refresh);
		
		
		Scene scene = new Scene(anchor, 1300, 500);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();


		
		
		
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
