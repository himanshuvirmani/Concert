/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.concert.web.rest;

import com.concert.domain.Entries;
import com.concert.service.EntriesService;
import com.concert.web.rest.errors.AggregateNotFoundException;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntriesController extends BaseController {

    @Autowired
    private EntriesService entriesService;

    @RequestMapping(value = "/actor_id/{actor_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Entries> getEntriesForActor(@PathVariable("actor_id") String actorId) {
        List<Entries> result = this.entriesService.getEntriesByActorId(actorId);
        if (result == null) {
            throw new AggregateNotFoundException();
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Entries> addEntries(@RequestBody List<Entries> entries) {
        if(CollectionUtils.isEmpty(entries)) return new ArrayList<>();
        List<Entries> result = this.entriesService.addEntries(entries);
        if (result == null) {
            throw new AggregateNotFoundException();
        }
        return result;
    }

}
