package flightDemo;

public class FlightData {
    private String src;
    private String des;
    private double cost;

    public FlightData(String src, String des, double cost) {
        this.src = src;
        this.des = des;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "FlightData{" +
                "src='" + src + '\'' +
                ", des='" + des + '\'' +
                ", cost=" + cost +
                '}';
    }
}
