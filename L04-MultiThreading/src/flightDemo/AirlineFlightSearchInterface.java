package flightDemo;

import java.util.List;

public interface AirlineFlightSearchInterface {
    List<FlightData> getFlightBySrcDes(String src, String des);
}
