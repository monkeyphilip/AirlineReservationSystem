package Gui;

import java.sql.*;

import Objects.Flights;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class SearchPage extends Application implements EventHandler<ActionEvent> {
	private ObservableList<ObservableList> data;
	private String usernameId = "";
	private Date depDate;
	private Date arrDate;
	private Time depTime;
	private Time arrTime;
	private Date newDepDate;
	private Date newArrDate;
	private Time newDepTime;
	private Time newArrTime;
	private Timestamp depTimestamp;
	private Timestamp arrTimestamp;
	private Timestamp newFlightDep;
	private int conflictCount = 0;
	private int countHolder;
	private int emptySeats;
	private int takenSeats;
	private int full;
	
	public Timestamp getNewFlightDep() {
		return newFlightDep;
	}

	public void setNewFlightDep(Timestamp newFlightDep) {
		this.newFlightDep = newFlightDep;
	}
	
	public Date getNewDepDate() {
		return newDepDate;
	}

	public void setNewDepDate(Date newDepDate) {
		this.newDepDate = newDepDate;
	}

	public Date getNewArrDate() {
		return newArrDate;
	}

	public void setNewArrDate(Date newArrDate) {
		this.newArrDate = newArrDate;
	}

	public Time getNewDepTime() {
		return newDepTime;
	}

	public void setNewDepTime(Time newDepTime) {
		this.newDepTime = newDepTime;
	}

	public Time getNewArrTime() {
		return newArrTime;
	}

	public void setNewArrTime(Time newArrTime) {
		this.newArrTime = newArrTime;
	}

	public Date getDepDate() {
		return depDate;
	}

	public void setDepDate(Date depDate) {
		this.depDate = depDate;
	}

	public Date getArrDate() {
		return arrDate;
	}

	public void setArrDate(Date arrDate) {
		this.arrDate = arrDate;
	}

	public Time getDepTime() {
		return depTime;
	}

	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}

	public Time getArrTime() {
		return arrTime;
	}

	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}

	public Timestamp getDepTimestamp() {
		return depTimestamp;
	}

	public void setDepTimestamp(Timestamp depTimestamp) {
		this.depTimestamp = depTimestamp;
	}

	public Timestamp getArrTimestamp() {
		return arrTimestamp;
	}

	public void setArrTimestamp(Timestamp arrTimestamp) {
		this.arrTimestamp = arrTimestamp;
	}

	public String getUsernameId() {
		return usernameId;
	}

	public void setUsernameId(String usernameId) {
		this.usernameId = usernameId;
	}

	public int getConflictCount() {
		return conflictCount;
	}

	public void setConflictCount(int conflictCount) {
		this.conflictCount = conflictCount;
	}

	public int getCountHolder() {
		return countHolder;
	}

	public int getEmptySeats() {
		return emptySeats;
	}

	public void setEmptySeats(int emptySeats) {
		this.emptySeats = emptySeats;
	}

	public int getTakenSeats() {
		return takenSeats;
	}

	public void setTakenSeats(int takenSeats) {
		this.takenSeats = takenSeats;
	}

	public int getFull() {
		return full;
	}

	public void setFull(int full) {
		this.full = full;
	}

	public void setCountHolder(int countHolder) {
		this.countHolder = countHolder;
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Search");
		AnchorPane anchor = new AnchorPane();
		anchor.setPadding(new Insets(20, 20, 20, 20));
		TableView<Flights> table = new TableView<>();
		final ObservableList<Flights> data = FXCollections.observableArrayList();
		
		ChoiceBox<String> dropdown = new ChoiceBox<>();
		dropdown.getItems().addAll("Destnation", "Origin", "Departure", "Airline");
		dropdown.setValue("Destnation");
		dropdown.setLayoutY(60);
		dropdown.setLayoutX(340);
		
		Button returnMain = new Button("Return Home");
		returnMain.setOnAction(e -> {
			MainPage main = new MainPage();
			try {
				main.start(primaryStage);
			}
			catch (Exception ex) {
			}
		});
		returnMain.setLayoutX(970);
		returnMain.setLayoutY(60);
		returnMain.setMinWidth(100);
		
		Button logOut = new Button("Log out");
		logOut.setOnAction(e -> {
			Login login = new Login();
			try {
				login.start(primaryStage);
			}
			catch (Exception ex) {
				
			}
		});
		logOut.setLayoutX(1080);
		logOut.setLayoutY(60);
		logOut.setMinWidth(100);
		
		Label userID = new Label();
		userID.setAlignment(javafx.geometry.Pos.CENTER);
		userID.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		userID.setLayoutX(1000.0);
		userID.setLayoutY(10.0);
		userID.setText("Logged in as: " + Login.getUser());
		userID.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
		userID.setFont(new Font(18.0));

		TextField searchTxt = new TextField();
		searchTxt.setLayoutX(460.0);
		searchTxt.setLayoutY(60.0);
		searchTxt.setMinWidth(250);

		TextField addFlight = new TextField();
		addFlight.setLayoutX(1200);
		addFlight.setLayoutY(300);
		
		try {
			Connection myConn;
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airlinedatabase", "root",
					"confident");

			String sqlUserCheck = "SELECT * FROM `Users` where username = '" + Login.getUser() + "'";
			
			Statement myStat = myConn.createStatement();
			
			ResultSet myRs;
			myRs = myStat.executeQuery(sqlUserCheck);

			
			int count = 0;
			while (myRs.next()) {
				count = count + 1;
				setUsernameId(myRs.getString("ssn"));
				System.out.println(getUsernameId());

			}

		} catch (Exception exc) {

		}

		Label addFlightLbl = new Label("Select Flight Number to Add");
		try {
			Connection myConn;
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airlinedatabaser", "root",
					"confident");

			String sqlUserCheck = "SELECT * FROM `Users` where username = '" + Login.getUser() + "'";
		
			Statement myStat = myConn.createStatement();
			
			ResultSet myRs;
			myRs = myStat.executeQuery(sqlUserCheck);

			
			int count = 0;
			while (myRs.next()) {
				count = count + 1;
				setUsernameId(myRs.getString("ssn"));
				System.out.println(getUsernameId());

			}

		} catch (Exception exc) {

		}

		
		addFlightLbl.setLayoutX(1200);
		addFlightLbl.setLayoutY(275);

		Button addFlightButton = new Button("Add Flight");
		addFlightButton.setLayoutX(1240);
		addFlightButton.setLayoutY(335);
		addFlightButton.setOnAction(e -> {
			try {
				Connection myConn;
				myConn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/airlinedatabase", "root", "confident" );

				String sqlFlightBook = "INSERT INTO 'FlightUser'(`Flights_num`,`Users_ssn`)VALUES("
						+ addFlight.getText().trim() + ", " + getUsernameId() + ")";

				String sqlFlightCheck = "SELECT `Flights_num`, `Users_ssn` FROM `FlightUser` where Users_ssn = '"
						+ getUsernameId() + "' and Flights_num= '" + addFlight.getText().trim() + "'";

				String sqlBookingCheck = "select  `num`,`departure_time`, `arrival_time`, `departure_date`, `arrival_date` from\r\n"
						+ "Flights inner Join FlightUser on Flight_num = flights.num \r\n"
						+ "inner join Users on FlightUser.Users_ssn = users.ssn where username = '"
						+ Login.getUser() + "'";

				String bookingCheckValue = "SELECT `departure_date`, `departure_time` FROM `Flights` where number ='"
						+ addFlight.getText().trim() + "'";

				
				Statement myStat = myConn.createStatement();
			
				ResultSet myRs;
				myRs = myStat.executeQuery(sqlFlightCheck);

				
				int count = 0;
				while (myRs.next()) {
					count = count + 1;

				}

				myRs = myStat.executeQuery(bookingCheckValue);
				while (myRs.next()) {
					setNewDepDate(myRs.getDate("departure_date"));
					setNewDepTime(myRs.getTime("departure_time"));
					setNewFlightDep(java.sql.Timestamp
							.valueOf(getNewDepDate().toString().concat(" " + getNewDepTime().toString())));
					
				}

				if (count == 0) {

					myRs = myStat.executeQuery(sqlBookingCheck);
					while (myRs.next()) {

						setDepDate(myRs.getDate("departure_date"));
						setArrDate(myRs.getDate("arrival_date"));
						setDepTime(myRs.getTime("departure_time"));
						setArrTime(myRs.getTime("arrival_time"));
						java.sql.Timestamp departure = java.sql.Timestamp
								.valueOf(getDepDate().toString().concat(" " + getDepTime().toString()));
						java.sql.Timestamp arrival = java.sql.Timestamp
								.valueOf(getArrDate().toString().concat(" " + getArrTime().toString()));

						setConflictCount(conflictCheck(departure, arrival, getNewFlightDep()));
						if (getConflictCount() == 1) {
							setCountHolder(1);
						}

						System.out.println(getConflictCount());

					}
					if (getCountHolder() == 0) {

						myStat.executeUpdate(sqlFlightBook);
					}

					else {
						AlertBox.display("Error", "This flight conflicts with other flights in your account");
					}

				}

				else {
					AlertBox.display("Error!", "Error! you have alredy booked flight number: "
							+ addFlight.getText().trim() + ". \n You cannot Book the same flight tiwce!");
				}
				myStat.close();
				myRs.close();
				myConn.close();
				
			}
			catch(Exception ex) {
				
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


		TableColumn<Flights, Time> column6 = new TableColumn<Flights, Time>("Departure Time");
		column6.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		column6.setMinWidth(128.88);

		TableColumn<Flights, Date> column7 = new TableColumn<Flights, Date>("Arrival Time");
		column7.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		column7.setMinWidth(128.88);
		
		TableColumn<Flights, Date> column5 = new TableColumn<Flights, Date>("Departure Date");
		column5.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
		column5.setMinWidth(128.88);

		TableColumn<Flights, Time> column8 = new TableColumn<Flights, Time>("Arrival Date");
		column8.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		column8.setMinWidth(128.88);

		TableColumn<Flights, Integer> column9 = new TableColumn<Flights, Integer>("Seats Open");
		column9.setCellValueFactory(new PropertyValueFactory<>("seatsOpen"));
		column9.setMinWidth(128.88);
		
		table.setTableMenuButtonVisible(false);

		Button searchButton = new Button("Search");
		searchButton.setLayoutX(715);
		searchButton.setLayoutY(60.0);
		searchButton.setMinWidth(60);
		searchButton.setOnAction(e -> {
			try {
			String dbSearch = getChoice(dropdown).trim();
			String searchItem = searchTxt.getText().trim();
			Connection myConn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/airlinedatabase", "root", "confident" );
			String sqlUserCheck = "SELECT * FROM Flights WHERE " + dbSearch + " = '" + searchItem + "'";
			
			PreparedStatement myStat = myConn.prepareStatement(sqlUserCheck);
			
			ResultSet myRs;
			myRs = myStat.executeQuery();
			table.getItems().clear();

			

			while (myRs.next()) {

				data.add(new Flights(myRs.getInt("num"), myRs.getString("airline"), myRs.getString("origin_city"),
						myRs.getString("destination_city"), myRs.getDate("departure_time"),
						myRs.getTime("arrivial_time"), myRs.getDate("departure_date"), myRs.getTime("arrival_date"),
						myRs.getInt("seats_open")));
				table.setItems(data);
			}
			myStat.close();
			myRs.close();
			myConn.close();
			}

			catch(Exception ex) {
				
			}
		});
		table.setLayoutY(100);
		table.setLayoutX(20);
		table.setMinWidth(1160);
		table.setMinHeight(580);
		table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);
		anchor.getChildren().addAll(dropdown, userID, searchTxt, searchButton, table, returnMain, logOut, addFlight,
				addFlightLbl, addFlightButton);
		
		Scene scene = new Scene(anchor, 1400, 700);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setMaximized(true);
	}
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public String getChoice(ChoiceBox<String> dropdown) {

		String dbSearch = "";
		String choice = dropdown.getValue();

		if (choice.equals("Destnation")) {
			dbSearch = "destination_city";
		}

		else if (choice.equals("Origin")) {
			dbSearch = "origin_city";
		} else if (choice.equals("Date")) {
			dbSearch = "departure_date";
		} else if (choice.equals("Airline")) {
			dbSearch = "airline";
		}
		return dbSearch;

	}

	public int conflictCheck(Timestamp d, Timestamp a, Timestamp d1) {

		if (d1.after(d) && d1.before(a)) {
			return 1;
		} else {
			return 0;
		}
		
		
	}

	
	
}
