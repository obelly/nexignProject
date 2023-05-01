package ru.nexign.crmservice.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nexign.crmservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin(String login);
}
