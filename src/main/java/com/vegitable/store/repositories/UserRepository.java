package com.vegitable.store.repositories;

import com.vegitable.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailId(String emailId);

    User findUserByEmailIdAndPassword(String emailId, String password);
}
