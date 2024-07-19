package dev.muhammad.learnsendingemails.repository;

import dev.muhammad.learnsendingemails.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    Boolean existByEmail(String email);
}
