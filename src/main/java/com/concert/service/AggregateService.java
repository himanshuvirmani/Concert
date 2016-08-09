package com.concert.service;

import com.concert.domain.Aggregate;

public interface AggregateService {

    Aggregate getAggregateByExternalId(String externalId);

}
