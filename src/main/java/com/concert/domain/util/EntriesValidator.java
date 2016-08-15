package com.concert.domain.util;

import com.concert.domain.Entries;
import com.concert.domain.Template;
import com.concert.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * Created by himanshu.virmani on 14/08/16.
 */
@Slf4j
@Component("entriesValidator")
public class EntriesValidator implements Validator{

    @Autowired
    private TemplateService templateService;

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "template", "template.empty");

        List<Entries> entries = (List<Entries>) target;
        Assert.notEmpty(entries);

        log.info("Entries is " + entries.toString());
        Assert.notNull(templateService);
        final Template template = templateService.getTemplateByName(entries.get(0).getTemplate());
        if(template == null) {
            log.error("No template found with name " + entries.get(0).getTemplate());
            errors.reject("No template found with name " + entries.get(0).getTemplate());
        }

    }
}
