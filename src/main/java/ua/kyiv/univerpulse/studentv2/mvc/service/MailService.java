package ua.kyiv.univerpulse.studentv2.mvc.service;

import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;

public interface MailService {

    void sendMessage(Person person);

}
