package events.repos;

import events.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoI extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer integer);
    boolean existsByName(String name);
    Optional<User> findByName(String name);
}
