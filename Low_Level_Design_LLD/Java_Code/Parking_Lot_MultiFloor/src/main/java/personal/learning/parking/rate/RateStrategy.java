package personal.learning.parking.rate;

import personal.learning.parking.ParkingTicket;

public interface RateStrategy {
	double calculatePayableAmount(ParkingTicket parkingTicket);
}
