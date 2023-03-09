package com.adem.springdata.repositories;

import com.adem.springdata.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
}
