@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private BankAcc from;
    @ManyToOne
    private BankAcc to;
    @Column(nullable = false)
    private Double amountMoney;

    public Transaction(BankAcc from, BankAcc to, Double amountMoney) {
        this.from = from;
        this.to = to;
        this.amountMoney = amountMoney;
    }

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BankAcc getFrom() {
        return from;
    }

    public void setFrom(BankAcc from) {
        this.from = from;
    }

    public BankAcc getTo() {
        return to;
    }

    public void setTo(BankAcc to) {
        this.to = to;
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Double amountMoney) {
        this.amountMoney = amountMoney;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", amountMoney=" + amountMoney +
                '}';
    }
}
