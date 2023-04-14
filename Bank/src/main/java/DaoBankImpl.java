import javax.management.Query;
import javax.persistence.*;
import javax.swing.text.html.parser.Entity;
import java.util.Currency;
import java.util.List;
import java.util.Random;
public class DaoBankImpl implements AutoCloseable{
    private final EntityManagerFacrtory emf = Persistence.createEntityManagerFactory("Bank");
    private final EntityManager em = emf.createEntityManager();

    public void addNewBankClient(Client client){
        commit(client);
    }
    public void addCurrencyRate(Currency currencyType,Double rate){
        CurrencyRate currencyRate = new CurrencyRate(currencyType,rate);
        commit(currencyRate);
    }
    public BankAcc getAcc(Client client, Currency currency){
        return client.getBankAcc()
                .stream()
                .filter(val -> val.getCurrency().equals(currency))
                .findFirst()
                .orElse(null);
    }
    public void transaction(BankAcc from, BankAcc to, Double amount, List<CurrencyRate> rates) {
        if (from.getAmount() < amount) {
            System.out.println("Not enough money on your account! " + from.getCurrency());
            return;
        }

        Currency fromCurrency = from.getCurrency();
        Currency toCurrency = to.getCurrency();

        double convertedAmount = getConvertedAmount(amount, rates, fromCurrency, toCurrency);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + convertedAmount);

        em.getTransaction().begin();
        em.merge(from);
        em.merge(to);
        em.getTransaction().commit();
        Transaction transaction = new Transaction(from, to, amount);
        commit(transaction);
        System.out.println("Transaction complete!");
    }
    private double getConvertedAmount(Double amount, List<CurrencyRate> rates, Currency fromCurrency, Currency toCurrency) {
        double convertedAmount = amount;
        if (fromCurrency != toCurrency) {
            if (fromCurrency.equals(Currency.UAH)) {
                convertedAmount /= getRate(rates, toCurrency);
            }
            if (fromCurrency.equals(Currency.USD)) {
                convertedAmount *= getRate(rates, Currency.USD);
                convertedAmount /= getRate(rates, toCurrency);
            }
            if (fromCurrency.equals(Currency.EUR)) {
                convertedAmount *= getRate(rates, Currency.EUR);
                convertedAmount /= getRate(rates, toCurrency);
            }
        }
        return convertedAmount;
    }
    private Double getRate(List<CurrencyRate> rates, Currency currency) {
        return rates.stream()
                .filter(rate -> rate.getCurrency() == currency)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rate not found for your value " + currency))
                .getExchangeRate();
    }
    public void totalCashOnAccount(Client client) {
        Double totalCash = 0.0;
        List<BankAcc> bankAcc = client.getBankAcc();
        Query query = em.createQuery("SELECT c FROM CurrencyRate c");
        List<CurrencyRate> currencyRates = query.getResultList();

        for (BankAcc account : bankAcc) {
            Double balance = account.getAmount();
            Currency currency = account.getCurrency();
            Double rate = 1.0;
            for (CurrencyRate currencyRate : currencyRates) {
                if (currencyRate.getCurrency().equals(currency)) {
                    rate = currencyRate.getExchangeRate();
                    break;
                }
            }
            totalCash += balance * rate;
        }
        System.out.println("Client " + client.getName() + "total cash amount is:  " + totalCash + " UAH");
    }
    public List<CurrencyRate> getCurrencyRates() {
        Query query = em.createQuery("SELECT v FROM CurrencyRate v");
        return (List<CurrencyRate>) query.getResultList();
    }

    public Client getClientByName(String name) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.clientName = :name");
        query.setParameter("name", name);
        return (Client) query.getSingleResult();
    }
    public void deposit(Client client, Double amount, Currency currencyType) {
        List<BankAcc> bankAccounts = client.getBankAcc();
        for (BankAcc bankAcc : bankAccounts) {
            if (bankAcc.getCurrency() == currencyType) {
                bankAcc.setAmount(bankAcc.getAmount() + amount);
                em.getTransaction().begin();
                em.merge(client);
                em.getTransaction().commit();
                break;
            }
        }
    }
    public void addNewBankAccount(Client client, Currency currency, Double amount) {
        Client client1 = em.find(Client.class, client.getId());
        BankAcc bankAcc = new BankAcc();
        bankAcc.setClient(client1);
        bankAcc.setAmount(amount);
        bankAcc.setCurrency(currency);
        bankAcc.setAccNumber(generateRandomNumber(currency));
        List<BankAcc> bankAcc = client1.getBankAcc();
        bankAcc.add(bankAcc);
        client1.setBankAcc(bankAcc);
        commit(bankAcc);
    }
    private <T> void commit(T t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            System.out.println("Your operation is successful!");
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public static String generateRandomNumber(Currency currency) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        if (currency.equals(Currency.UAH)) {
            sb.append("UAH");
        }
        if (currency.equals(Currency.USD)) {
            sb.append("USD");
        }
        if (currency.equals(Currency.EUR)) {
            sb.append("EUR");
        }
        for (int i = 0; i < 27; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
}

