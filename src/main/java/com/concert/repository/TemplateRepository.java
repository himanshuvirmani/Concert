package com.concert.repository;

import com.concert.domain.Template;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by himanshu.virmani on 10/08/16.
 */
public interface TemplateRepository extends MongoRepository<Template, String> {

    Template findByName(String name);
}
