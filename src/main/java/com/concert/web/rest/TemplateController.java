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
import com.concert.domain.Template;
import com.concert.service.TemplateService;
import com.concert.web.rest.errors.AggregateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Template> getAllTempates() {
        List<Template> result = templateService.getAllTemplates();
        if (result == null) {
            throw new AggregateNotFoundException();
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Template addTemplate(@RequestBody Template template) {
        Template result = this.templateService.addTemplate(template);
        if (result == null) {
            throw new AggregateNotFoundException();
        }
        return result;
    }

}
