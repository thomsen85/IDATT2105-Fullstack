package edu.ntnu.idatt2105.calculator.repository;

import edu.ntnu.idatt2105.calculator.model.User;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(@NonNull String username);
  Optional<User> findByEmail(@NonNull String email);
}
