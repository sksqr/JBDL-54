package flightDemo;

import java.util.ArrayList;
import java.util.List;

public class GoAirFlightSearchService implements AirlineFlightSearchInterface{

    @Override
    public List<FlightData> getFlightBySrcDes(String src, String des) {
        //API call to goAir server
        List<FlightData> flightDataList = new ArrayList<>();
        flightDataList.add(new FlightData("DLI","BLR",9000.00));
        return flightDataList;
    }
}
