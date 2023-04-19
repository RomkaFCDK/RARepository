import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class calculatingController {
        private final Repository repository;

        public calculatingController(Repository repository) {
            this.repository = repository;
        }

        @GetMapping("/calculate")
        public Long calculate(@RequestParam String type,
                              @RequestParam Long x,
                              @RequestParam Long y) {
            MyDataBase db = new MyDataBase(type, x, y);
            long result;
            switch (type) {
                case "addition" -> {
                    result = x + y;
                    db.setResult(result);
                    repository.save(db);
                }
                case "multiply" -> {
                    result = x * y;
                    db.setResult(result);
                    repository.save(db);
                }
                default -> throw new IllegalStateException("Illegal value" + type);
            }
            return result;
        }
    }

