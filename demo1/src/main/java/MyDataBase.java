import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MyDataBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Long x;
    private Long y;
    private Long result;

    public MyDataBase(String type, Long x, Long y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
