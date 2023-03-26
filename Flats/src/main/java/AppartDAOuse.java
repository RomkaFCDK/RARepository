import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppartDAOuse implements AppartmentDAO{
    private final Connection connection;

    public AppartDAOuse(Connection connection) {
        this.connection = connection;
    }
    public void createTable() {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Appartment");
            st.execute("CREATE TABLE Appartment (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " region VARCHAR(40) NOT NULL, " +
                    "address VARCHAR(40) NOT NULL ," +
                    "area DECIMAL(20,2) NOT NULL ," +
                    "rooms INT NOT NULL ," +
                    "price DECIMAL(30,2) NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAppartment(String region, String address, Double area, Integer rooms, Double price) {
        try (PreparedStatement st = connection.prepareStatement("INSERT INTO Appartment (region,address,area,rooms,price) VALUES (?,?,?,?,?)")) {
            st.setString(1, region);
            st.setString(2, address);
            st.setDouble(3, area);
            st.setInt(4, rooms);
            st.setDouble(5, price);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appartment> getAppartment() {
        List<Appartment> result = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM Appartment")) {
                getList(result, rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Appartment> getAppartmentByFilter(String filter) {
        List<Appartment> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Appartment WHERE region = ? OR address = ?")) {
            ps.setString(1, filter);
            ps.setString(2, filter);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public List<Appartment> getAppartmentByFilter(Double filter) {
        List<Appartment> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Appartment WHERE area BETWEEN ? AND ? OR price BETWEEN ? AND ?")) {
            ps.setDouble(1, filter - 50);
            ps.setDouble(2, filter + 50);
            ps.setDouble(3, filter - 1000);
            ps.setDouble(4, filter + 1000);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Appartment> getAppartmentByFilter(Integer filter) {
        List<Appartment> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Apartments WHERE rooms = ?")) {
            ps.setInt(1, filter);
            ResultSet rs = ps.executeQuery();
            getList(result, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private void getList(List<Appartment> result, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Appartment appartment = new Appartment();
            appartment.setId(rs.getInt(1));
            appartment.setRegion(rs.getString(2));
            appartment.setAddress(rs.getString(3));
            appartment.setArea(rs.getDouble(4));
            appartment.setRooms(rs.getInt(5));
            appartment.setPrice(rs.getDouble(6));
            result.add(appartment);
        }
    }
}
