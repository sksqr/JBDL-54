package flightDemo;

import java.util.ArrayList;
import java.util.List;

public class AirIndiaFlightSearchService implements AirlineFlightSearchInterface{
    @Override
    public List<FlightData> getFlightBySrcDes(String src, String des) {
        //API call to AirIndia Server

        List<FlightData> flightDataList = new ArrayList<>();
        flightDataList.add(new FlightData("DLI","BLR",12000.00));
        return flightDataList;
    }
}
