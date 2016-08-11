package com.concert.repository;

import com.concert.domain.Entries;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by himanshu.virmani on 10/08/16.
 */
public interface EntriesRepository extends MongoRepository<Entries, String> {

    List<Entries> findByActorId(String actorId);
}
