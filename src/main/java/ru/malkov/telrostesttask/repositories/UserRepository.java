package ru.malkov.telrostesttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malkov.telrostesttask.entities.User;

/**
 * Extension of {@link JpaRepository}, typed with {@link User} as entity and {@link Long} as ID
 */

public interface UserRepository extends JpaRepository<User, Long> {

}
