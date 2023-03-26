import java.sql.*;
import java.util.Map;

public class OrdersDAOuse implements OrdersDAO {
    private final Connection connection;

    public OrdersDAOuse(Connection connection) {
        this.connection = connection;
    }

    public void addClient(String surname, String name, String phone, String email) {
        Map<Integer, Client> clientMap = viewAllClients();
        for (Map.Entry<Integer, Client> entry : clientMap.entrySet()) {
            if (entry.getValue().getPhone().equals(phone)) {
                System.out.println("Client is already in base");
                return;
            }
        }
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO clients (surname,name,phone,email) +
                "VALUES (?,?,?,?)")) {
            ps.setString(1, surname);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewGoods(String name, Double price) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO goods (name,price)" +
                "VALUES (?,?,)")) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewOrder(Client client, Goods goods) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (client_id,goods_id)" +
                "VALUES (?,?)")) {

            Map<Integer, Client> clientList = viewAllClients();
            Map<Integer, Goods> goodsList = viewAllGoods();

            if (clientList.containsKey(client.getId()) && goodsList.containsKey(goods.getId())) {

                ps.setInt(1, client.getId());
                ps.setInt(2, goods.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, Client> viewAllClients() {
        Map<Integer, Client> result = new HashMap<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM clients")) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt(1));
                    client.setSurname(rs.getString(2));
                    client.setName(rs.getString(3));
                    client.setPhone(rs.getString(4));
                    client.setEmail(rs.getString(5));
                    result.put(client.getId(), client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Map<Integer, Goods> viewAllGoods() {
        Map<Integer, Goods> result = new HashMap<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM goods")) {
                while (rs.next()) {
                    Goods goods = new Goods();
                    goods.setId(rs.getInt(1));
                    goods.setName(rs.getString(2));
                    goods.setPrice(rs.getDouble(3));
                    result.put(goods.getId(), goods);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Map<Integer, Order> viewAllOrders() {
        Map<Integer, Order> result = new HashMap<>();
        Map<Integer, Client> clientMap = viewAllClients();
        Map<Integer, Goods> productMap = viewAllGoods();

        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM orders")) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt(1));
                    order.setClient(clientMap.get(rs.getInt(2)));
                    order.setGoods(productMap.get(rs.getInt(3)));
                    result.put(order.getId(), order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Goods checkGoods(String name) {
        Map<Integer, Goods> temp = viewAllGoods();
        for (Map.Entry<Integer, Goods> goods : temp.entrySet()) {
            if (goods.getValue().getName().equalsIgnoreCase(name)) {
                return goods.getValue();
            }
        }
        return null;
    }

    public Client checkClient(Client client) {
        Map<Integer, Client> temp = viewAllClients();
        for (Map.Entry<Integer, Client> clients : temp.entrySet()) {
            if (clients.getValue().getPhone().equalsIgnoreCase(client.getPhone())) {
                System.out.println("Client is already exists");
                return null;
            }
        }
        return client;
    }

    public Client getClientByEmail(String email) {
        Map<Integer, Client> temp = viewAllClients();
        for (Map.Entry<Integer, Client> client : temp.entrySet()) {
            if (client.getValue().getEmail().equalsIgnoreCase(email))
                return client.getValue();
        }
        return null;
    }
}


