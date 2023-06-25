package flightDemo;

import java.util.ArrayList;
import java.util.List;

public class MmtDemo {
    public static void main(String[] args) {
        List<AirlineFlightSearchInterface> airlineFlightSearchInterfaceList = new ArrayList<>();
        airlineFlightSearchInterfaceList.add(new GoAirFlightSearchService());
        airlineFlightSearchInterfaceList.add(new IndigoFlightSearchService());
        airlineFlightSearchInterfaceList.add(new AirIndiaFlightSearchService());
        MmtSearchService mmtSearchService = new MmtSearchService(airlineFlightSearchInterfaceList);
        List<FlightData> data = mmtSearchService.getFlightDataBySrcDes("DLI","BLR");
        System.out.println(data);
    }

}
