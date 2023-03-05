package com.adem.springdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adem.springdata.entities.Article;

@Repository
public interface ArticleRepository extends CrudRepository <Article, Long>
{

}