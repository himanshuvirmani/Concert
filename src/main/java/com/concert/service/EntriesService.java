package com.concert.service;

import com.concert.domain.Aggregate;
import com.concert.domain.Entries;

import java.util.List;

public interface EntriesService {

    List<Entries> getEntriesByActorId(String actorId);

}
