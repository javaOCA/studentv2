package ua.kyiv.univerpulse.studentv2.mvc.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonMailException;
import ua.kyiv.univerpulse.studentv2.mvc.service.MailService;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;

import static ua.kyiv.univerpulse.studentv2.mvc.domain.ActionEnum.PROCESS;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private final static Logger logger = Logger.getLogger(MailServiceImpl.class);
    @Resource
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void sendMessage(Person person) {
        StringBuilder sbText = new StringBuilder();
        StringBuilder sbSubject = new StringBuilder();
        switch (person.getEnlist().getAction()) {
            case PROCESS:
                sbText.append(messageSource.getMessage("auth.greeting", null, LocaleContextHolder.getLocale())
                        + " " + person.getFirstName() + "! "
                        + messageSource.getMessage("auth.congratulation", null, LocaleContextHolder.getLocale()));
                sbSubject.append(messageSource.getMessage("subj.registered", null, LocaleContextHolder.getLocale()));
                break;
            case YES:
                sbText.append(messageSource.getMessage("auth.greeting.yes", null, LocaleContextHolder.getLocale())
                        + " " + person.getFaculty().getName() + ".");
                sbSubject.append(messageSource.getMessage("subj.registered.yes", null, LocaleContextHolder.getLocale()));
                break;
            case INTERVIEW:
                sbText.append(messageSource.getMessage("auth.greeting", null, LocaleContextHolder.getLocale())
                        + " " + person.getFirstName() + "! "
                        + messageSource.getMessage("auth.greeting.interview", null, LocaleContextHolder.getLocale())
                        + " " + LocalDate.now().plusDays(7L) + ".");
                sbSubject.append(messageSource.getMessage("subj.registered.interview", null, LocaleContextHolder.getLocale()));
                break;
            case NO:
                sbText.append(messageSource.getMessage("auth.greeting", null, LocaleContextHolder.getLocale())
                        + " " + person.getFirstName() + "! "
                        + messageSource.getMessage("auth.greeting.no", null, LocaleContextHolder.getLocale())
                        + " " + person.getFaculty().getName() + ".");
                sbSubject.append(messageSource.getMessage("subj.registered.no", null, LocaleContextHolder.getLocale()));
                break;
        }
        MimeMessagePreparator preparator = getMessagePreparator(person, sbText.toString(), sbSubject.toString());
        sbText.setLength(0);
        sbSubject.setLength(0);
        try{
            mailSender.send(preparator);
            logger.info("Message send");
        } catch (MailException e) {
            logger.error("Error send message to student", e);
            throw new PersonMailException(e.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(Person person, String text, String subject) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress(env.getProperty("email.from")));
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(env.getProperty("email.test.to")));
                mimeMessage.setText(text);
                mimeMessage.setSubject(subject);
            }
        };
        return preparator;
    }

}
