package com.concert.service.impl;

import com.concert.domain.Template;
import com.concert.repository.TemplateRepository;
import com.concert.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("templateService")
@Transactional
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }


    @Override
    public List<Template> getAllTemplates() {
        return templateRepository.findAll();
    }

    @Override
    public Template addTemplate(Template template) {
        //XXX Add validations for template requirements.
        return templateRepository.save(template);
    }

    @Override
    public Template getTemplateByName(String name) {
        return templateRepository.findByName(name);
    }
}
