package smu.likelion.Traditional.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.likelion.Traditional.Market.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
