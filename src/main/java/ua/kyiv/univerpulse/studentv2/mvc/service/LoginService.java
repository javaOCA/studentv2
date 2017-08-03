package ua.kyiv.univerpulse.studentv2.mvc.service;

import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

public interface LoginService {

    PersonDto verifyLogin(String login, String password);

}
