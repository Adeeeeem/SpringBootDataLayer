package com.adem.springdata.repositories;

import com.adem.springdata.entities.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adem.springdata.entities.Provider;

import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository <Provider, Long>
{
	@Query("FROM Article a WHERE a.provider.id = ?1")
    List<Article> findArticlesByProvider(long id);
}