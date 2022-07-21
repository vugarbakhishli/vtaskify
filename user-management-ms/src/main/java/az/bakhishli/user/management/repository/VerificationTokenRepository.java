package az.bakhishli.user.management.repository;

import az.bakhishli.user.management.domain.User;
import az.bakhishli.user.management.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    void deleteByToken(String token);

    Optional<VerificationToken> findByUser(User user);
}
