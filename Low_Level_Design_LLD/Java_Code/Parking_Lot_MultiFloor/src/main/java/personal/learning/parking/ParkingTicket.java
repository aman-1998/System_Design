package personal.learning.parking;

import java.time.LocalDateTime;

public class ParkingTicket {
	
	private String ticketId;
	private Vehicle vehicle;
	private ParkingSpot parkingSpot;
	private LocalDateTime startTime;
	
	public ParkingTicket(String ticketId, LocalDateTime startTime) {
		this.ticketId = ticketId;
		this.startTime = startTime;
	}
	
	public String getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}
	
	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	@Override
	public String toString() {
		return "ParkingTicket [ticketId=" + ticketId + ", vehicle=" + vehicle + ", parkingSpot=" + parkingSpot
				+ ", startTime=" + startTime + "]";
	}
	
}
