package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private RegistrationService registrationService;
    private MessageSource messageSource;
    @Autowired
    public RegistrationController(RegistrationService registrationService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET, name = "registrationPerson")
    public String registrationPerson(Model model, HttpServletRequest request) {
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new PersonDto());
            HttpSession session = request.getSession(true);
            session.setAttribute("faculties", registrationService.getAllFaculty());
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("person") @Valid PersonDto personDto,
                           BindingResult result, RedirectAttributes attributes,
                           HttpServletRequest request) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.person", result);
            personDto.setPassword("");
            attributes.addFlashAttribute("person", personDto);
            return "redirect:/registration";
        }
        if(registrationService.findPersonByLogin(personDto)) {
            attributes.addFlashAttribute("error",
                    messageSource.getMessage("login.exists", null, LocaleContextHolder.getLocale()));
            personDto.setPassword("");
            attributes.addFlashAttribute("person", personDto);
            return "redirect:/registration";
        }
        if(registrationService.findEvaluationDateByFaculty(personDto)) {
            attributes.addFlashAttribute("error",
                    messageSource.getMessage("date.exists", null, LocaleContextHolder.getLocale()));
            personDto.setPassword("");
            attributes.addFlashAttribute("person", personDto);
            return "redirect:/registration";
        }
        HttpSession session = request.getSession(true);
        personDto.setPassword(DigestUtils.md5Hex(personDto.getPassword() + personDto.getLogin()));
        session.setAttribute("person", personDto);
        return "redirect:/address";

    }
}
