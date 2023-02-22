package com.adem.springdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adem.springdata.entities.Provider;

@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long>
{

}