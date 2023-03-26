import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import  java.util.Map;
public class Main {
    public static void main(String[] args){
        try (Connection connection = ConnectionFactory.getConnection("DBOrdersProperies");
             Scanner sc = new Scanner(System.in)) {

            OrdersDAOuse dao = new OrdersDAOuse(connection);
            while (true) {
                System.out.println("1: Add your client");
                System.out.println("2: Add your goods");
                System.out.println("3: Add new order");
                System.out.println("4: View all clients");
                System.out.println("5: View all goods");
                System.out.println("6: View all orders");
                System.out.print("Input your choice: ");
                String choice = sc.nextLine();

                if (choose(sc, dao, choice)) return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean choose(Scanner sc, OrdersDAOuse dao, String choice) {
        switch (choice) {
            case "1":
                addClient(sc, dao);
                break;
            case "2":
                addGoods(sc, dao);
                break;
            case "3":
                addOrder(sc, dao);
                break;
            case "4":
                Map<Integer, Client> clientList = dao.viewAllClients();
                for (Map.Entry<Integer, Client> clientEntry : clientList.entrySet()) {
                    System.out.println(clientEntry.getValue());
                }
                break;
            case "5":
                Map<Integer, Goods> goodsList = dao.viewAllGoods();
                for (Map.Entry<Integer, Goods> productEntry : goodsList.entrySet()) {
                    System.out.println(productEntry.getValue());
                }
                break;
            case "6":
                Map<Integer, Order> orderList = dao.viewAllOrders();
                for (Map.Entry<Integer, Order> order : orderList.entrySet()) {
                    System.out.println(order.getValue());
                }
                break;
            default:
                return true;
        }
        return false;
    }
    private static void addOrder(Scanner sc, OrdersDAOuse dao) {
        System.out.println("Input order details:");
        System.out.println("Input client name:");
        String name = sc.nextLine();
        System.out.println("Input client surname:");
        String surname = sc.nextLine();
        System.out.println("Input client phone number:");
        String phone = sc.nextLine();
        System.out.println("Input client email:");
        String email = sc.nextLine();
        Client client = new Client(name, surname, phone, email);
        if (dao.checkClient(client) != null) {
            dao.addClient(client.getName(), client.getSurname(), client.getPhone(), client.getEmail());
        }
        System.out.println("Input name of goods:");
        String nameOfGoods = sc.nextLine();
        Goods goods = dao.checkGoods(nameOfGoods);
        if (goods == null) {
            System.out.println("Goods are not found!");
        } else {
            dao.createNewOrder(dao.getClientByEmail(client.getEmail()), goods);
        }
        return;
    }
    private static void addGoods(Scanner sc, OrdersDAOuse dao) {
        System.out.println("Input goods name:");
        String goodsName = sc.nextLine();
        System.out.println("Input price:");
        String price = sc.nextLine();
        dao.addNewGoods(goodsName, Double.parseDouble(price));
    }
    private static void addClient(Scanner sc, OrdersDAOuse dao) {
        System.out.println("Input name: ");
        String name = sc.nextLine();
        System.out.println("Input surname: ");
        String surname = sc.nextLine();
        System.out.println("Input phone: ");
        String phone = sc.nextLine();
        System.out.println("Input email: ");
        String email = sc.nextLine();
        dao.addClient(name, surname, phone, email);

        return;

    }
}
