import java.util.Map;

public interface OrdersDAO {
    void addClient(String surname, String name, String phone, String email);
    void addNewGoods(String name, Double price);
    void createNewOrder(Client client, Goods goods);
    Map<Integer, Client> viewAllClients();
    Map<Integer, Goods> viewAllGoods();
    Map<Integer, Order> viewAllOrders();
}
