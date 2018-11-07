package Objects;

import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;


public class Flights {
	
	private String airline;
	private String originCity;
	private String destinationCity;
	private Time depatureTime;
	private Time arrivalTime;
	private Date departureDate;
	private Date arrivalDate;
	private int flightNumber;
	private int flightCapacity;
	private int seatsOpen;
	private int seatsTaken;
	private boolean checkIfSeatFull;
	private Timestamp time;
	
	public Flights() {
		
	}
	
	public Flights(String airline, int flightNumber, String originCity, String destinationCity) {
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		
	}
	
	public Flights(String airline, String originCity, String destinationCity, int flightNumber, Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime, int seatsOpen, Timestamp time) {
		
		this.airline = airline;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.flightNumber = flightNumber;
		this.departureDate = departureDate;
		this.depatureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.seatsOpen = seatsOpen;
		this.time = time;
		
		
	}
	
	public Flights(String airline, String originCity, String destinationCity, int flightNumber, Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime, Timestamp time) {
		
		this.airline = airline;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.flightNumber = flightNumber;
		this.departureDate = departureDate;
		this.depatureTime = departureTime;
		this.arrivalDate =arrivalDate;
		this.arrivalTime = arrivalTime;
		this.time = time;
		
	}
	
	
	public boolean checkIfSeatFull() {
		return checkIfSeatFull;
	}
	public void setFull(boolean checkIfSeatFull) {
		this.checkIfSeatFull = checkIfSeatFull;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public Time getDepatureTime() {
		return depatureTime;
	}
	public void setDepatureTime(Time depatureTime) {
		this.depatureTime = depatureTime;
	}
	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public int getFlightCapacity() {
		return flightCapacity;
	}
	public void setFlightCapacity(int flightCapacity) {
		this.flightCapacity = flightCapacity;
	}
	public int getSeatsOpen() {
		return seatsOpen;
	}
	public void setSeatsOpen(int seatsOpen) {
		this.seatsOpen = seatsOpen;
	}
	public int getSeatsTaken() {
		return seatsTaken;
	}
	public void setSeatsTaken(int seatsTaken) {
		this.seatsTaken = seatsTaken;
	}
	

}
