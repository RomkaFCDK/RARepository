public class Appartment {

    private int id;
    private String region;
    private String address;
    private Double area;
    private Integer rooms;
    private Double price;

    public Appartment() {
    }

    public Appartment(String region, String address, Double area, Integer rooms, Double price) {
        this.region = region;
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Appartment{" +
                "region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}

