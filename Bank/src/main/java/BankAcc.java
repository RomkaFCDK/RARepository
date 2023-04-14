import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;

public class BankAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String accNumber;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Client client;
    private Currency currency;
    private Double amount = 0.0;
    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL)
    private List<Transaction> sendPayment = new ArrayList<>();
    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL)
    private List<Transaction> receivePayment = new ArrayList<>();

    public BankAcc(String accNumber,Client client,Currency currency) {
        this.accNumber = accNumber;
        this.client = client;
        this.currency = currency;
    }

    public BankAcc() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Transaction> getSendPayment() {
        return sendPayment;
    }

    public void setSendPayment(List<Transaction> sendPayment) {
        this.sendPayment = sendPayment;
    }

    public List<Transaction> getReceivePayment() {
        return receivePayment;
    }

    public void setReceivePayment(List<Transaction> receivePayment) {
        this.receivePayment = receivePayment;
    }

    @Override
    public String toString() {
        return "BankAcc{" +
                "id=" + id +
                ", accNumber='" + accNumber + '\'' +
                ", client=" + client +
                ", currency=" + currency +
                ", amount=" + amount +
                ", sendPayment=" + sendPayment +
                ", receivePayment=" + receivePayment +
                '}';
    }
}
