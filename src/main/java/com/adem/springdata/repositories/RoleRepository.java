package com.adem.springdata.repositories;

import com.adem.springdata.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository <Role, Integer>
{
    Role findByName(String name);
}