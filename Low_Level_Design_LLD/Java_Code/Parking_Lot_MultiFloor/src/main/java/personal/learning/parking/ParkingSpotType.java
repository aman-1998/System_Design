package personal.learning.parking;

public enum ParkingSpotType {
	BIKESPOT(30.0),
    CARSPOT(60.0),
    TRUCKSPOT(100.0);

    private final double ratePerHour; // in Rupees

    ParkingSpotType(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }
}
