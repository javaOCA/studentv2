package ua.kyiv.univerpulse.studentv2.mvc.controller;

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
import ua.kyiv.univerpulse.studentv2.mvc.domain.Person;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;
import ua.kyiv.univerpulse.studentv2.mvc.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class AddressController {

    private RegistrationService registrationService;
    private MessageSource messageSource;
    @Autowired
    public AddressController(RegistrationService registrationService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String registrationAddress(Model model) {
        if (!model.containsAttribute("address")) {
            model.addAttribute("address", new AddressDto());
        }
        return "address";
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("address") @Valid AddressDto addressDto,
                             BindingResult result, RedirectAttributes attributes,
                             HttpServletRequest request) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.address", result);
            attributes.addFlashAttribute("address", addressDto);
            return "redirect:/address";
        }
        HttpSession session = request.getSession();
        PersonDto personDto = (PersonDto) session.getAttribute("person");
        Person person = registrationService.findPersonByNameAndAddress(personDto, addressDto);
        if(Objects.nonNull(person)) {
            attributes.addFlashAttribute("error",
                    messageSource.getMessage("person.exists", null, LocaleContextHolder.getLocale()));
            personDto.setPassword("");
            attributes.addFlashAttribute("person", personDto);
            return "redirect:/registration";
        } else {
            session.setAttribute("address", addressDto);
            return "redirect:/marks";
        }
    }
}
