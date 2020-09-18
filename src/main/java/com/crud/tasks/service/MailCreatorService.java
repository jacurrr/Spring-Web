package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailCreatorService {
    @Qualifier("templateEngine")
    private final TemplateEngine templateEngine;
    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;

    public String buildTrelloCardEmail(String message){
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Terllo Acount");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message","Dodano nową kartę do Trello");
        context.setVariable("message", message);
        context.setVariable("tasks_url","http://localhost:8888/crud");
        context.setVariable("button","Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("companyName", companyConfig.getCompanyName());
        context.setVariable("companyMail",companyConfig.getCompanyMail());
        context.setVariable("goodbye_message","Pozdrawiam :)");
        context.setVariable("show_button", false);
        context.setVariable("is_friend",false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
