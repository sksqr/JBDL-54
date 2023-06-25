package flightDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MmtSearchService {
    private List<AirlineFlightSearchInterface> airlineFlightSearchInterfaceList;
    private ExecutorService executorService;

    public MmtSearchService(List<AirlineFlightSearchInterface> airlineFlightSearchInterfaceList) {
        this.airlineFlightSearchInterfaceList = airlineFlightSearchInterfaceList;
        executorService = Executors.newFixedThreadPool(20);
    }
    public List<FlightData> getFlightDataBySrcDes(String src, String des){
        List<Callable<List<FlightData>>> callableList = new ArrayList<>();
        List<FlightData> response = new ArrayList<>();
        for(AirlineFlightSearchInterface flightSearchInterface :airlineFlightSearchInterfaceList){
            callableList.add(new Callable<List<FlightData>>() {
                @Override
                public List<FlightData> call() throws Exception {
                    return flightSearchInterface.getFlightBySrcDes(src,des);
                }
            });
        }

        try {
            List<Future<List<FlightData>>> futureList = executorService.invokeAll(callableList);
            for(Future<List<FlightData>> future : futureList){
                response.addAll(future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
