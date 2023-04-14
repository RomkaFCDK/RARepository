import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDEMTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @OneToMany(mappedBy = "client",cascade = CascadeType.REFRESH)
    private List<BankAcc> bankAcc = new ArrayList<>(3);

    public Client(String name) {
        this.name = name;
    }

    public Client(String name,List<BankAcc> bankAcc) {
        this.name = name;
        this.bankAcc = bankAcc;
    }

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankAcc> getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(List<BankAcc> bankAcc) {
        this.bankAcc = bankAcc;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bankAcc=" + bankAcc +
                '}';
    }
}
