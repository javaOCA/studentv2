package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.domain.RoleEnum;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private LoginService loginService;
    private MessageSource messageSource;
    @Autowired
    public LoginController(LoginService loginService, MessageSource messageSource) {
        this.loginService = loginService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, name = "loginPerson")
    public String loginPerson(Model model) {
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new PersonDto());
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String verifyLogin(@ModelAttribute("person") PersonDto personDto,
                              HttpSession session,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              RedirectAttributes attributes) {
        if (logger.isDebugEnabled())
            logger.debug("Before invoke verifyLogin method");
        StringBuilder action = new StringBuilder();
        personDto = loginService.verifyLogin(personDto.getLogin(), personDto.getPassword());
        if (logger.isDebugEnabled())
            logger.debug("After invoke verifyLogin method");
        if (Objects.nonNull(personDto)) {
            session.setAttribute("person", personDto);
            switch (personDto.getRole().getRole()) {
                case ROLE_ADMIN:
                    action.append("redirect:/admin");
                    break;
                case ROLE_USER:
                    action.append("redirect:/result");
                    break;
            }
        } else {
            attributes.addFlashAttribute("error",
                    messageSource.getMessage("login.incorrect", null, LocaleContextHolder.getLocale()));
            action.append("redirect:/login");
        }
        return action.toString();
    }
}
