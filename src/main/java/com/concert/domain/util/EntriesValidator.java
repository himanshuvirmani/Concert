package com.concert.domain.util;

import com.concert.domain.Attribute;
import com.concert.domain.Entries;
import com.concert.domain.Template;
import com.concert.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by himanshu.virmani on 14/08/16.
 */
@Slf4j
@Component("entriesValidator")
public class EntriesValidator implements Validator {

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
        Assert.notNull(templateService);
        for (Entries entry : entries) {
            final Template template = templateService.getTemplateByName(entry.getTemplate());
            if (template == null) {
                errors.reject("No template found with name " + entry.getTemplate());
                return;
            }
            if (entry.getEntryAttributes().isEmpty()) {
                errors.reject("No Attributes found with Entry type" + entry.getType() + "and actor id" + entry.getActorId());
                return;
            }

            validateAttributeTemplate(template, entry.getEntryAttributes(), errors);

            removeExtraAttributes(template, entry.getEntryAttributes());
        }

    }

    private void validateAttributeTemplate(Template template, Map<String, Object> entryAttributes, Errors errors) {
        for (String key : template.getAttributeMap().keySet()) {
            if (!entryAttributes.containsKey(key)) {
                final Attribute attribute = template.getAttributeMap().get(key);
                if (attribute.isRequired()) {
                    errors.reject("Need attribute with name " + key);  //required params should be there in entry attributes
                    return;
                }
            } else {
                final Attribute attribute = template.getAttributeMap().get(key);
                if (attribute.getType().equals("DateTime")) {
                    try {
                        DateTime date = DateTime.parse((String) entryAttributes.get(key));
                        log.info("Key Matched  " + key + "for type " + attribute.getType());
                    } catch (UnsupportedOperationException | IllegalArgumentException e) {
                        e.printStackTrace();
                        errors.reject("Wrong date time value " + entryAttributes.get(key));
                    }
                } else {
                    if (attribute.getType().equalsIgnoreCase(entryAttributes.get(key).getClass().getSimpleName())) {
                        log.info("Key Matched  " + key + "for type " + attribute.getType());
                    } else {
                        log.info("Key Did not Matched  " + key + "for template type " + attribute.getType() +
                                "and " + entryAttributes.get(key).getClass().getSimpleName());
                        errors.reject("Key Did not Matched  " + key + "for template type " + attribute.getType() +
                                "and " + entryAttributes.get(key).getClass().getSimpleName());
                    }
                }
            }
        }
    }

    private void removeExtraAttributes(Template template, Map<String, Object> entryAttributes) {
        // remove extra unneeded attributes
        Iterator itr = entryAttributes.keySet().iterator();
        while (itr.hasNext()) {
            if (!template.getAttributeMap().containsKey(itr.next())) {
                itr.remove();
            }
        }
    }
}
