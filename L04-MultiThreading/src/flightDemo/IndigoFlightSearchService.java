package flightDemo;

import java.util.ArrayList;
import java.util.List;

public class IndigoFlightSearchService implements AirlineFlightSearchInterface{
    @Override
    public List<FlightData> getFlightBySrcDes(String src, String des) {
        List<FlightData> flightDataList = new ArrayList<>();
        flightDataList.add(new FlightData("DLI","BLR",10000.00));
        return flightDataList;
    }
}
