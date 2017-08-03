package ua.kyiv.univerpulse.studentv2.mvc.service;

import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

public interface MailService {

    void sendMessage(PersonDto personDto);

}
