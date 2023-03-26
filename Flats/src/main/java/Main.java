import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection("DataBase_Properties");
             Scanner sc = new Scanner(System.in)) {
             AppartmentDAO dao = new AppartDAOuse(connection);
            System.out.println("Create new table? Y/N?");
            String answer = sc.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                dao.createTable();
            }
            while (true) {
                System.out.println("1: add new appartment");
                System.out.println("2: view all appartments");
                System.out.println("3: get all appartments by filter");
                System.out.print("Input your num: ");
                String choose = sc.nextLine();

                switch (choose) {

                    case "1":
                        addAppartmentCase(sc, dao);
                        break;

                    case "2":
                        viewAppartmentCase(dao);
                        break;

                    case "3":
                        if (getAllCase(sc, dao)) return;
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean getAllCase(Scanner sc, AppartmentDAO dao) {
        System.out.println("Choose your filer:");
        System.out.println("1: filter by region or address");
        System.out.println("2: filter by price or area of appartments");
        System.out.println("3: filter by number of rooms");
        System.out.print("Input your choice: ");
        String filterNumber = sc.nextLine();
        System.out.print("Input filter parameter:  ");
        String filter = sc.nextLine();
        if (filteredCase(dao, filterNumber, filter)) return true;
        return false;
    }
    private static void viewAppartmentCase(AppartmentDAO dao) {
        List<Appartment> apartments = dao.getAppartment();
        if (apartments.isEmpty()) {
            System.out.println("NO SUCH DATA");
        } else {
            for (Appartment app :
                    apartments) {
                System.out.println(app);
            }
        }
    }
    private static boolean filteredCase(AppartmentDAO dao, String filterNumber, String filter) {
        switch (filterNumber) {
            case "1":
                List<Appartment> filterByRegionAndAddress = dao.getAppartmentByFilter(filter);
                if (filterByRegionAndAddress.isEmpty()) {
                    System.out.println("No match");
                } else {
                    for (Appartment app : filterByRegionAndAddress) {
                        System.out.println(app);
                    }
                }
                break;
            case "2":
                List<Appartment> filterByAreaAndPrice = dao.getAppartmentByFilter(Double.parseDouble(filter));
                if (filterByAreaAndPrice.isEmpty()) {
                    System.out.println("No match");
                } else {
                    for (Appartment app : filterByAreaAndPrice) {
                        System.out.println(app);
                    }
                }
                break;
            case "3":
                List<Appartment> filterByRooms = dao.getAppartmentByFilter(Integer.parseInt(filter));
                if (filterByRooms.isEmpty()) {
                    System.out.println("No match");
                } else {
                    for (Appartment app : filterByRooms) {
                        System.out.println(app);
                    }
                }
                break;
            default:
                return true;
        }
        return false;
    }
    private static void addAppartmentCase(Scanner sc, AppartmentDAO dao) {
        StringJoiner sj = new StringJoiner(",");
        System.out.println("Enter your region:");
        sj.add(sc.nextLine());
        System.out.println("Enter your address:");
        sj.add(sc.nextLine());
        System.out.println("Enter the area of appartment:");
        sj.add(sc.nextLine());
        System.out.println("Enter number of rooms:");
        sj.add(sc.nextLine());
        System.out.println("Enter price:");
        sj.add(sc.nextLine());
        String[] temp = sj.toString().split(",");
        dao.addAppartment(temp[0], temp[1], Double.parseDouble(temp[2]), Integer.parseInt(temp[3]), Double.parseDouble(temp[4]));
        }
}


