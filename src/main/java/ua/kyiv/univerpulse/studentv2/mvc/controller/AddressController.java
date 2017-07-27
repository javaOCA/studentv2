package ua.kyiv.univerpulse.studentv2.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.kyiv.univerpulse.studentv2.mvc.dto.AddressDto;
import ua.kyiv.univerpulse.studentv2.mvc.dto.PersonDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AddressController {

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
        session.setAttribute("address", addressDto);
        return "redirect:/marks";
    }
}
