package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailCreatorService {
    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;
    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("preview_message","Dodano nową kartę do Trello");
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8888/crud");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("companyName", companyConfig.getCompanyName());
        context.setVariable("companyMail",companyConfig.getCompanyMail());
        context.setVariable("goodbye_message","Pozdrawiam :)");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
