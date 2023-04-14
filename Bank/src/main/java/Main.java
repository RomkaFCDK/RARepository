import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try (DaoBankImpl dao = new DaoBankImpl()){
            try (Scanner sc = new Scanner(System.in)) {
                while (true) {
                    String choice = showClientMenu(sc);
                    switch (choice) {
                        case "1" -> addNewBankClientAndNewAccount(dao, sc);
                        case "2" -> addNewAccountToClient();Client(dao, sc);
                        case "3" -> depositByCurrencyType(dao, sc);
                        case "4" -> Transaction(dao, sc);
                        case "5" -> dao.totalCashOnAccount(getClientByName(dao, sc));
                        case "6" -> addCurrencyRates(dao);
                        default -> {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void Transaction(DaoBankImpl dao, Scanner sc) {
        Client client = getClientByName(dao, sc);
        System.out.println("From whom funds would be transfered?");
        System.out.println("From:");
        BankAcc from = chooseCurrency(dao, sc, client);
        System.out.println("To:");
        BankAcc to = chooseCurrency(dao, sc, client);
        System.out.println("Input amount of funds:");
        String amount = sc.nextLine();
        dao.transaction(from, to, Double.parseDouble(amount),dao.getCurrencyRates());
    }
    private static BankAcc chooseCurrency(DaoBankImpl dao, Scanner sc, Client client) {
        BankAcc from = null;
        System.out.println("""
                1: UAH
                2: USD
                3: EUR""");
        String choiceFrom = sc.nextLine();
        switch (choiceFrom) {
            case "1" -> from = dao.getAcc(client, Currency.UAH);
            case "2" -> from = dao.getAcc(client, Currency.USD);
            case "3" -> from = dao.getAcc(client, Currency.EUR);
        }
        return from;
    }
    private static void addCurrencyRates(DaoBankImpl dao) {
        dao.addCurrencyRate(Currency.UAH, 1.0);
        dao.addCurrencyRate(Currency.USD, 37.5);
        dao.addCurrencyRate(Currency.EUR, 39.3);
    }
    private static void depositByCurrencyType(DaoBankImpl dao, Scanner scanner) {
        Client client = getClientByName(dao, scanner);
        System.out.println("""
                1: UAH
                2: USD
                3: EUR
                Enter currency type to deposit:""");
        String currencyType = scanner.nextLine();
        System.out.println("Enter the amount of money to deposit account:");
        String amount = scanner.nextLine();
        switch (currencyType) {
            case "1" -> dao.deposit(client, Double.parseDouble(amount), Currency.UAH);
            case "2" -> dao.deposit(client, Double.parseDouble(amount), Currency.USD);
            case "3" -> dao.deposit(client, Double.parseDouble(amount), Currency.EUR);
        }
    }
    private static void addNewAccountToClient(DaoBankImpl dao, Scanner scanner) {
        Client client = getClientByName(dao, scanner);
        choseTypeOfBankAccount(dao, scanner, client);
    }
    private static Client getClientByName(DaoBankImpl dao, Scanner scanner) {
        System.out.println("Input client name:");
        String clientName = scanner.nextLine();
        return dao.getClientByName(clientName);
    }
    private static void addNewBankClientAndNewAccount(DaoBankImpl dao, Scanner scanner) {
        System.out.println("Input client name:");
        String clientName = scanner.nextLine();
        Client client = new Client(clientName);
        dao.addNewBankClient(Client(client));
        System.out.println("You want open to this client " + client.getName() + " new bank account: Y/N?");
        String choice2 = scanner.nextLine();
        addNewAccount(dao, scanner, client, choice2);
    }
    private static void addNewAccount(DaoBankImpl dao, Scanner scanner, Client client, String choice2) {
        if (choice2.equalsIgnoreCase("N")) {
            return;
        }
        choseTypeOfBankAccount(dao, scanner, client);
    }
    private static void choseTypeOfBankAccount(DaoBankImpl dao, Scanner scanner, Client client) {
        System.out.println("Chose currency type of this account:");
        System.out.println("1: USD");
        System.out.println("2: EUR");
        System.out.println("3: UAH");
        String currencyType = scanner.nextLine();
        switch (currencyType) {
            case "1" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.USD, Double.parseDouble(amount));
            }
            case "2" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.EUR, Double.parseDouble(amount));
            }
            case "3" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.UAH, Double.parseDouble(amount));
            }
        }
    }
    private static String showClientMenu(Scanner scanner) {
        System.out.println("1: add new bank client");
        System.out.println("2: add new bank account to client");
        System.out.println("3: deposit was found to account");
        System.out.println("4: transfer your money");
        System.out.println("5: get all cash in UAH");
        System.out.println("6: add currency rates");
        System.out.println("Input your choice:");
        return scanner.nextLine();
    }
}




