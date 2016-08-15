package com.concert.service;

import com.concert.domain.Template;

import java.util.List;

public interface TemplateService {

    List<Template> getAllTemplates();

    Template addTemplate(Template template);

    Template getTemplateByName(String name);
}
