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
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.exception.PersonMailException;
import ua.kyiv.univerpulse.studentv2.mvc.service.MailService;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    public void sendMessage(PersonDto personDto) {
        logger.info("Invoke method sendMessage");
        MimeMessagePreparator preparator = getMessagePreparator(personDto);
        try{
            mailSender.send(preparator);
            logger.info("Message send");
        } catch (MailException e) {
            logger.error("Error send message to student", e);
            throw new PersonMailException(e.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final PersonDto personDto) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress(env.getProperty("email.from")));
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(env.getProperty("email.test.to")));
                mimeMessage.setText(messageSource.getMessage("auth.greeting", null, LocaleContextHolder.getLocale())
                        + " " + personDto.getFirstName() + "! "
                        + messageSource.getMessage("auth.congratulation", null, LocaleContextHolder.getLocale()));
                mimeMessage.setSubject("You have registered now");
            }
        };
        return preparator;
    }

}
