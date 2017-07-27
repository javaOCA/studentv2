package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration", method = RequestMethod.GET, name = "registrationPerson")
    public String registrationPerson(Model model) {
        if (!model.containsAttribute("person")) {
            model.addAttribute("person", new PersonDto());
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
        HttpSession session = request.getSession(true);
        session.setAttribute("person", personDto);
        return "redirect:/address";
    }

}
