package com.concert.service.impl;

import com.concert.domain.Entries;
import com.concert.repository.EntriesRepository;
import com.concert.service.EntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("entriesService")
@Transactional
public class EntriesServiceImpl implements EntriesService {

    private final EntriesRepository entriesRepository;

    @Autowired
    public EntriesServiceImpl(EntriesRepository entriesRepository) {
        this.entriesRepository = entriesRepository;
    }


    @Override
    public List<Entries> getEntriesByActorId(String actorId) {
        List<Entries> entries = entriesRepository.findByActorId(actorId);
        return entries;
    }

    @Override
    public List<Entries> addEntries(List<Entries> entries) {
        return entriesRepository.save(entries);
    }
}
