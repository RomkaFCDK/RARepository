package academy.prog.javaprobot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByChatId(long chatId);

    Optional<User> findUserByPhone(String phone);
}
