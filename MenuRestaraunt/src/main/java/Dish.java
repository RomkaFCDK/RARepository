import javax.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO);
    private int id;

    @Column(name = "dishName");
    private String name;
    private double price;
    private double weight;
    @Column(name = "discount")
    private boolean isDiscountAvailible;

    public Dish() {
    }

    public Dish(String name,double price,double weight,boolean isDiscountAvailible) {

        this.name = name;
        this.price = price;
        this.weight = weight;
        this.isDiscountAvailible = isDiscountAvailible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscountAvailible() {
        return isDiscountAvailible;
    }

    public void setDiscountAvailible(boolean discountAvailible) {
        isDiscountAvailible = discountAvailible;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", isDiscountAvailible=" + isDiscountAvailible +
                '}';
    }
}
