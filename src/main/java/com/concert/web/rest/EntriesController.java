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
import com.concert.domain.util.EntriesValidator;
import com.concert.service.EntriesService;
import com.concert.web.rest.errors.AggregateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entries")
@Slf4j
public class EntriesController extends BaseController {

    @Autowired
    private EntriesService entriesService;

    @Autowired
    private EntriesValidator entriesValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(entriesValidator);
    }

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
    public List<Entries> addEntries(@Valid @RequestBody List<Entries> entries, BindingResult bindingResult ) {
        if(CollectionUtils.isEmpty(entries)) return new ArrayList<>();
        if( bindingResult.hasErrors())
        {
            throw new AggregateNotFoundException(bindingResult.getAllErrors().get(0).getCode());
        }
        List<Entries> result = this.entriesService.addEntries(entries);
        if (result == null) {
            throw new AggregateNotFoundException();
        }
        return result;
    }

}
