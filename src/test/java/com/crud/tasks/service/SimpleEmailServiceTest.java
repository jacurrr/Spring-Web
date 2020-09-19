package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com","Test","Test Message", "test1@test.com");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());
        //When
        simpleEmailService.send(mail);
        //Then
        verify(simpleEmailService, times(1)).send(mail);
    }
}