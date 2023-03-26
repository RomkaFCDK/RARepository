import java.util.List;

public interface AppartmentDAO{
    void createTable();
    void addAppartment(String region, String address, Double area, Integer rooms, Double price);

    List<Appartment> getAppartment();

    List<Appartment> getAppartmentByFilter(String filter);

    List<Appartment> getAppartmentByFilter(Double filter);

    List<Appartment> getAppartmentByFilter(Integer filter);
}

