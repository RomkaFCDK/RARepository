import jdk.jfr.events.CertificateId;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "flats2")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_flat")
    private long id;

    @Column(nullable = false)
    private String district;

    @Column (nullable = false)
    private String address;
    private int square;
    private long price;

    public Flat() {
    }

    public Flat(String district,String address,int square,long price) {
        this.district = district;
        this.address = address;
        this.square = square;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", square=" + square +
                ", price=" + price +
                '}';
    }
}
